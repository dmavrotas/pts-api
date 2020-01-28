package com.dmavrotas.pts.api.utils;

import com.dmavrotas.pts.api.models.Car;
import com.dmavrotas.pts.api.repositories.RepositoryTestHelper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test has to test the behavior of the class AssignedIdentityGenerator
 * Mocking it is very hard and complex, so the best way to test it is by testing its expected behavior on some models.
 */
class AssignedIdentityGeneratorTest extends RepositoryTestHelper
{
    @Test
    void testBehavior()
    {
        var car = new Car();

        car.setId(1);
        car.setRegistrationPlate("EG-721-NF");
        car.setCreated(LocalDateTime.now());

        var savedCar = carRepository.save(car);

        assertEquals(1, savedCar.getId());

        car = new Car();

        car.setRegistrationPlate("EG-721-NB");
        car.setCreated(LocalDateTime.now());

        savedCar = carRepository.save(car);

        assertEquals(2, savedCar.getId());

        car = new Car();

        car.setId(1000);
        car.setRegistrationPlate("EG-751-NB");
        car.setCreated(LocalDateTime.now());

        savedCar = carRepository.save(car);

        assertEquals(1000, savedCar.getId());

        car = new Car();

        car.setRegistrationPlate("EG-752-NB");
        car.setCreated(LocalDateTime.now());

        savedCar = carRepository.save(car);

        assertEquals(1001, savedCar.getId());

        savedCar = carRepository.save(car);

        assertEquals(1001, savedCar.getId());
    }
}
