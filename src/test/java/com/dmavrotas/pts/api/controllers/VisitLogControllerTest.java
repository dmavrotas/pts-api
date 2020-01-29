package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.*;
import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.models.enums.EPricingPolicy;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class VisitLogControllerTest extends BaseControllerTest
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

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/pts/visitLog/1", MockMvcResultMatchers.status().isOk(), savedVisitLog))
               .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/pts/visitLog", MockMvcResultMatchers.status().isOk(), List.of(savedVisitLog)))
               .andExpect(MockMvcResultMatchers.status().isOk());

        visitLog = new VisitLog();

        visitLog.setId(2);
        visitLog.setParking(savedParking);
        visitLog.setCar(savedCar);
        visitLog.setEntryTime(LocalDateTime.now());

        var savedVisitLog2 = visitLogRepository.save(visitLog);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pts/visitLog", MockMvcResultMatchers.status().isOk(),
                                                   List.of(savedVisitLog, savedVisitLog2))).andExpect(
                MockMvcResultMatchers.status().isOk());

        visitLog = new VisitLog();

        visitLog.setId(3);
        visitLog.setParking(savedParking);
        visitLog.setCar(savedCar);
        visitLog.setEntryTime(LocalDateTime.now());
        visitLog.setExitTime(LocalDateTime.now());

        var postContent = post("/api/pts/visitLog")
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .content(objectMapper.writeValueAsString(visitLog));

        mockMvc.perform(postContent).andExpect(MockMvcResultMatchers.status().isOk());

        var savedVisitLog4 = visitLogRepository.findById(3);

        assertNotNull(savedVisitLog4);

        mockMvc.perform(delete("/api/pts/visitLog/3")).andExpect(MockMvcResultMatchers.status().isOk());

        var deletedVisitLog = visitLogRepository.findById(3).orElse(null);

        assertNull(deletedVisitLog);
    }
}
