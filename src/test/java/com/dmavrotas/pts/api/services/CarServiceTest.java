package com.dmavrotas.pts.api.services;

import com.dmavrotas.pts.api.models.Car;
import com.dmavrotas.pts.api.repositories.RepositoryTestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CarServiceTest extends RepositoryTestHelper
{
    @Autowired
    private CarService carService;

    @Test
    void testService()
    {
        var car = new Car();

        car.setRegistrationPlate("EG-721-NF");
        car.setCreated(LocalDateTime.now());

        var savedCar = carService.saveEntity(car);

        assertNotNull(savedCar);
        assertEquals(1, ((ArrayList) carService.getAllEntities()).size());

        savedCar.setRegistrationPlate("AB-831-EG");

        savedCar = carService.saveEntity(savedCar);

        assertNotNull(savedCar);
        assertEquals(1, ((ArrayList) carService.getAllEntities()).size());

        car = new Car();

        car.setRegistrationPlate("EF-721-BE");
        car.setCreated(LocalDateTime.now());

        savedCar = carService.saveEntity(car);

        assertNotNull(savedCar);
        assertEquals(2, ((ArrayList) carService.getAllEntities()).size());
        assertEquals("EF-721-BE", savedCar.getRegistrationPlate());

        assertNotNull(carService.getCarByRegistrationPlate("EF-721-BE"));
        assertNull(carService.getCarByRegistrationPlate(null));
        assertNull(carService.getCarByRegistrationPlate("AA-BB-AAA"));

        assertNull(carService.getEntity(-2));

        assertNotNull(carService.getEntity(savedCar.getId()));

        assertTrue(carService.deleteEntity(savedCar));
        assertEquals(1, ((ArrayList) carService.getAllEntities()).size());

        assertFalse(carService.deleteEntity(null));
        assertNull(carService.saveEntity(null));
    }
}
