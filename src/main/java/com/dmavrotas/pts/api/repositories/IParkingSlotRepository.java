package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.ParkingSlot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IParkingSlotRepository extends CrudRepository<ParkingSlot, Integer>
{
    /**
     *
     * @param parkingId
     * @param parkingSlotTypeId
     * @return
     */
    @Query("SELECT s " +
           "FROM ParkingSlot s " +
           "where s.isFree = true " +
           "AND s.parking.id = :parkingId " +
           "AND s.parkingSlotType.id = :parkingSlotTypeId")
    List<ParkingSlot> findFreeSlotsForType(@Param(value = "parkingId") int parkingId,
                                           @Param(value = "parkingSlotTypeId") int parkingSlotTypeId);

    /**
     * @param carId
     * @return
     */
    ParkingSlot findParkingSlotByCarId(int carId);
}
