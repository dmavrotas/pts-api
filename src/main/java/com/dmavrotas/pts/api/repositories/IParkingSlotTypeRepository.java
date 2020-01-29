package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.ParkingSlotType;
import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import org.springframework.data.repository.CrudRepository;

public interface IParkingSlotTypeRepository extends CrudRepository<ParkingSlotType, Integer>
{
    /**
     * @param name
     * @return
     */
    ParkingSlotType findParkingSlotTypeByName(EParkingSlotType name);
}
