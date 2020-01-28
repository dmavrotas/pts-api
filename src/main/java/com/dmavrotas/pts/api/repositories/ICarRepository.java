package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.Car;
import org.springframework.data.repository.CrudRepository;

public interface ICarRepository extends CrudRepository<Car, Integer>
{
}
