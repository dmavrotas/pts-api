package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.ParkingSlot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IParkingSlotRepository extends CrudRepository<ParkingSlot, Integer>
{
    /**
     * Find a free parking slot for a specific parking for a specific parkingSlotType
     *
     * @param parkingId         The parking Id
     * @param parkingSlotTypeId The parkingSlotTypeId
     * @return The List of available free parkingSlots for this type.
     */
    @Query("SELECT s " +
           "FROM ParkingSlot s " +
           "where s.isFree = true " +
           "AND s.parking.id = :parkingId " +
           "AND s.parkingSlotType.id = :parkingSlotTypeId")
    List<ParkingSlot> findFreeSlotsForType(@Param(value = "parkingId") int parkingId,
                                           @Param(value = "parkingSlotTypeId") int parkingSlotTypeId);

    /**
     * Find in which parkingSlot a car is parked
     *
     * @param carId The car id
     * @return The Parking Slot for this car
     */
    ParkingSlot findParkingSlotByCarId(int carId);
}
