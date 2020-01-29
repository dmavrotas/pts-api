package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.Car;
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

class CarControllerTest extends BaseControllerTest
{
    @Test
    void testApi() throws Exception
    {
        var car = new Car();

        car.setId(1);
        car.setRegistrationPlate("EG-721-NF");
        car.setCreated(LocalDateTime.now());

        var savedCar = carRepository.save(car);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pts/car/1", MockMvcResultMatchers.status().isOk(), savedCar))
               .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/pts/car", MockMvcResultMatchers.status().isOk(), List.of(savedCar)))
               .andExpect(MockMvcResultMatchers.status().isOk());

        car = new Car();

        car.setId(2);
        car.setRegistrationPlate("EG-722-NF");
        car.setCreated(LocalDateTime.now());

        var savedCar2 = carRepository.save(car);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pts/car", MockMvcResultMatchers.status().isOk(),
                                                   List.of(savedCar, savedCar2))).andExpect(
                MockMvcResultMatchers.status().isOk());

        car = new Car();

        car.setId(2);
        car.setRegistrationPlate("EG-722-NF");
        car.setCreated(LocalDateTime.now());

        var postContent = post("/api/pts/car")
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .content(objectMapper.writeValueAsString(car));

        mockMvc.perform(postContent).andExpect(MockMvcResultMatchers.status().isOk());

        var savedCar4 = carRepository.findById(3);

        assertNotNull(savedCar4);

        mockMvc.perform(delete("/api/pts/car/3")).andExpect(MockMvcResultMatchers.status().isOk());

        var deletedCar = carRepository.findById(3).orElse(null);

        assertNull(deletedCar);
    }
}
