package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.ParkingSlotType;
import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
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

class ParkingSlotTypeControllerTest extends BaseControllerTest
{
    @Test
    void testApi() throws Exception
    {
        var parkingSlotType = new ParkingSlotType();

        parkingSlotType.setId(1);
        parkingSlotType.setName(EParkingSlotType.STANDARD);
        parkingSlotType.setCreated(LocalDateTime.now());

        var savedParkingSlotType = parkingSlotTypeRepository.save(parkingSlotType);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pts/parkingSlotType/1", MockMvcResultMatchers.status().isOk(),
                                                   savedParkingSlotType))
               .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/pts/parkingSlotType", MockMvcResultMatchers.status().isOk(),
                                           List.of(savedParkingSlotType)))
               .andExpect(MockMvcResultMatchers.status().isOk());

        parkingSlotType = new ParkingSlotType();

        parkingSlotType.setId(2);
        parkingSlotType.setName(EParkingSlotType.HIGH_ELECTRICAL_POWER);
        parkingSlotType.setCreated(LocalDateTime.now());

        var savedParkingSlotType2 = parkingSlotTypeRepository.save(parkingSlotType);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pts/parkingSlotType", MockMvcResultMatchers.status().isOk(),
                                                   List.of(savedParkingSlotType, savedParkingSlotType2))).andExpect(
                MockMvcResultMatchers.status().isOk());

        parkingSlotType = new ParkingSlotType();

        parkingSlotType.setId(3);
        parkingSlotType.setName(EParkingSlotType.LOW_ELECTRICAL_POWER);
        parkingSlotType.setCreated(LocalDateTime.now());

        var postContent = post("/api/pts/parkingSlotType")
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .content(objectMapper.writeValueAsString(parkingSlotType));

        mockMvc.perform(postContent).andExpect(MockMvcResultMatchers.status().isOk());

        var savedParkingSlotType4 = parkingSlotTypeRepository.findById(3);

        assertNotNull(savedParkingSlotType4);

        mockMvc.perform(delete("/api/pts/parkingSlotType/3")).andExpect(MockMvcResultMatchers.status().isOk());

        var deletedParkingSlotType = parkingSlotTypeRepository.findById(3).orElse(null);

        assertNull(deletedParkingSlotType);
    }
}
