package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.Car;
import org.springframework.data.repository.CrudRepository;

public interface ICarRepository extends CrudRepository<Car, Integer>
{
    /**
     * Find a car from its registration plate (which is unique)
     *
     * @param registrationPlate The registration plate in question
     * @return The car with the aforementioned registration plate
     */
    Car getCarByRegistrationPlate(String registrationPlate);
}
