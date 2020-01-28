package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.ParkingSlotType;
import org.springframework.data.repository.CrudRepository;

public interface IParkingSlotTypeRepository extends CrudRepository<ParkingSlotType, Integer>
{
}
