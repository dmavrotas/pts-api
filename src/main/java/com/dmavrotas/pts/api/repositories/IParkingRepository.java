package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.Parking;
import org.springframework.data.repository.CrudRepository;

public interface IParkingRepository extends CrudRepository<Parking, Integer>
{
}
