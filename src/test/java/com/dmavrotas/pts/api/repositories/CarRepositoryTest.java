package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.Car;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CarRepositoryTest extends RepositoryTestHelper
{
    @Test
    void repositoryTest()
    {
        assertEquals(0, ((ArrayList)carRepository.findAll()).size());

        var car = new Car();

        car.setId(1);
        car.setRegistrationPlate("EG-721-NF");
        car.setCreated(LocalDateTime.now());

        var savedCar = carRepository.save(car);

        assertNotNull(savedCar);
        assertEquals("EG-721-NF", savedCar.getRegistrationPlate());
    }
}
