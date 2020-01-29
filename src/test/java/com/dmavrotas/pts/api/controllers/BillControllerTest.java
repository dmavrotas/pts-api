package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.*;
import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.models.enums.EPricingPolicy;
import com.dmavrotas.pts.api.services.dto.BillDto;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class BillControllerTest extends BaseControllerTest
{
    @Test
    void testApi() throws Exception
    {
        var pricingPolicy = new PricingPolicy();

        pricingPolicy.setId(1);
        pricingPolicy.setName(EPricingPolicy.PER_HOUR);
        pricingPolicy.setFormula(new FixedPlusPerHourPricingPolicy(0.5f, 2.5f));
        pricingPolicy.setCreated(LocalDateTime.now());

        var savedPricingPolicy = pricingPolicyRepository.save(pricingPolicy);

        var parkingSlotType = new ParkingSlotType();

        parkingSlotType.setId(1);
        parkingSlotType.setName(EParkingSlotType.STANDARD);
        parkingSlotType.setCreated(LocalDateTime.now());

        var savedParkingSlotType = parkingSlotTypeRepository.save(parkingSlotType);

        var parking = new Parking();

        parking.setId(1);
        parking.setName("Nice Massena");
        parking.setPricingPolicy(pricingPolicy);
        parking.setCreated(LocalDateTime.now());

        var savedParking = parkingRepository.save(parking);

        var car = new Car();

        car.setId(1);
        car.setRegistrationPlate("EG-721-NF");
        car.setCreated(LocalDateTime.now());

        var savedCar = carRepository.save(car);

        var parkingSlot = new ParkingSlot();

        parkingSlot.setId(1);
        parkingSlot.setFree(true);
        parkingSlot.setParking(parking);
        parkingSlot.setCar(car);
        parkingSlot.setParkingSlotType(savedParkingSlotType);
        parkingSlot.setCreated(LocalDateTime.now());

        var savedParkingSlot = parkingSlotRepository.save(parkingSlot);

        var visitLog = new VisitLog();

        visitLog.setId(1);
        visitLog.setCar(car);
        visitLog.setParking(parking);
        visitLog.setEntryTime(LocalDateTime.now());
        visitLog.setExitTime(LocalDateTime.now().plusDays(1));

        var savedVisitLog = visitLogRepository.save(visitLog);

        var bill = new Bill();

        bill.setId(1);
        bill.setParkingSlot(parkingSlot);
        bill.setVisitLog(visitLog);
        bill.setAmount(2.0);
        bill.setCreated(LocalDateTime.now());

        var savedBill = billRepository.save(bill);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/pts/bill/1", MockMvcResultMatchers.status().isOk(), savedBill))
               .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/pts/bill", MockMvcResultMatchers.status().isOk(), List.of(savedBill)))
               .andExpect(MockMvcResultMatchers.status().isOk());

        bill = new Bill();

        bill.setId(2);
        bill.setParkingSlot(parkingSlot);
        bill.setVisitLog(visitLog);
        bill.setAmount(5.0);
        bill.setCreated(LocalDateTime.now());

        var savedBill2 = billRepository.save(bill);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pts/bill", MockMvcResultMatchers.status().isOk(),
                                                   List.of(savedBill, savedBill2))).andExpect(
                MockMvcResultMatchers.status().isOk());

        bill = new Bill();

        bill.setId(3);
        bill.setParkingSlot(parkingSlot);
        bill.setVisitLog(visitLog);
        bill.setAmount(7.0);
        bill.setCreated(LocalDateTime.now());

        var postContent = post("/api/pts/bill")
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .content(objectMapper.writeValueAsString(bill));

        mockMvc.perform(postContent).andExpect(MockMvcResultMatchers.status().isOk());

        var savedBill4 = billRepository.findById(3);

        assertNotNull(savedBill4);

        mockMvc.perform(delete("/api/pts/bill/3")).andExpect(MockMvcResultMatchers.status().isOk());

        var deletedBill = billRepository.findById(3).orElse(null);

        assertNull(deletedBill);

        var billDto = new BillDto(1, 1, 1);

        postContent = post("/api/pts/bill/payment").contentType(MediaType.APPLICATION_JSON).content(
                objectMapper.writeValueAsString(billDto));

        mockMvc.perform(postContent).andExpect(MockMvcResultMatchers.status().isOk());

        assertEquals(3, ((ArrayList) billRepository.findAll()).size());
        assertNotNull(billRepository.findById(4));
    }
}
