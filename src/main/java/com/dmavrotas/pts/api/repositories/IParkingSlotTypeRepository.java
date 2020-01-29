package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.ParkingSlotType;
import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import org.springframework.data.repository.CrudRepository;

public interface IParkingSlotTypeRepository extends CrudRepository<ParkingSlotType, Integer>
{
    /**
     * Find parkingSlot type by Name
     *
     * @param name The name of the parkingSlotType
     * @return The parkingSlotType found
     */
    ParkingSlotType findParkingSlotTypeByName(EParkingSlotType name);
}
