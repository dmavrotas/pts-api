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

class ParkingSlotControllerTest extends BaseControllerTest
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

        mockMvc.perform(MockMvcRequestBuilders
                                .get("/api/pts/parkingSlot/1", MockMvcResultMatchers.status().isOk(), savedParkingSlot))
               .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/pts/parkingSlot", MockMvcResultMatchers.status().isOk(), List.of(savedParkingSlot)))
               .andExpect(MockMvcResultMatchers.status().isOk());

        parkingSlot = new ParkingSlot();

        parkingSlot.setId(2);
        parkingSlot.setCar(car);
        parkingSlot.setParkingSlotType(savedParkingSlotType);
        parkingSlot.setFree(false);
        parkingSlot.setParking(parking);
        parkingSlot.setCreated(LocalDateTime.now());

        var savedParkingSlot2 = parkingSlotRepository.save(parkingSlot);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pts/parkingSlot", MockMvcResultMatchers.status().isOk(),
                                                   List.of(savedParkingSlot, savedParkingSlot2))).andExpect(
                MockMvcResultMatchers.status().isOk());

        parkingSlot = new ParkingSlot();

        parkingSlot.setId(3);
        parkingSlot.setCar(null);
        parkingSlot.setParkingSlotType(savedParkingSlotType);
        parkingSlot.setFree(true);
        parkingSlot.setParking(parking);
        parkingSlot.setCreated(LocalDateTime.now());

        var postContent = post("/api/pts/parkingSlot")
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .content(objectMapper.writeValueAsString(parkingSlot));

        mockMvc.perform(postContent).andExpect(MockMvcResultMatchers.status().isOk());

        var savedParkingSlot4 = parkingSlotRepository.findById(3);

        assertNotNull(savedParkingSlot4);

        mockMvc.perform(delete("/api/pts/parkingSlot/3")).andExpect(MockMvcResultMatchers.status().isOk());

        var deletedParkingSlot = parkingSlotRepository.findById(3).orElse(null);

        assertNull(deletedParkingSlot);
    }
}
