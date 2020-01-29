package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.Parking;
import com.dmavrotas.pts.api.models.PricingPolicy;
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

class ParkingControllerTest extends BaseControllerTest
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

        var parking = new Parking();

        parking.setId(1);
        parking.setName("Nice Massena");
        parking.setPricingPolicy(pricingPolicy);
        parking.setCreated(LocalDateTime.now());

        var savedParking = parkingRepository.save(parking);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/pts/parking/1", MockMvcResultMatchers.status().isOk(), savedParking))
               .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/pts/parking", MockMvcResultMatchers.status().isOk(), List.of(savedParking)))
               .andExpect(MockMvcResultMatchers.status().isOk());

        parking = new Parking();

        parking.setId(2);
        parking.setPricingPolicy(pricingPolicy);
        parking.setName("Parking 2");
        parking.setCreated(LocalDateTime.now());

        var savedParking2 = parkingRepository.save(parking);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pts/parking", MockMvcResultMatchers.status().isOk(),
                                                   List.of(savedParking, savedParking2))).andExpect(
                MockMvcResultMatchers.status().isOk());

        parking = new Parking();

        parking.setId(3);
        parking.setPricingPolicy(pricingPolicy);
        parking.setName("Parking 3");
        parking.setCreated(LocalDateTime.now());

        var postContent = post("/api/pts/parking")
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .content(objectMapper.writeValueAsString(parking));

        mockMvc.perform(postContent).andExpect(MockMvcResultMatchers.status().isOk());

        var savedParking4 = parkingRepository.findById(3);

        assertNotNull(savedParking4);

        mockMvc.perform(delete("/api/pts/parking/3")).andExpect(MockMvcResultMatchers.status().isOk());

        var deletedParking = parkingRepository.findById(3).orElse(null);

        assertNull(deletedParking);
    }
}
