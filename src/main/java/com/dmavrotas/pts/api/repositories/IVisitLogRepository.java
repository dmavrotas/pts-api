package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.VisitLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IVisitLogRepository extends CrudRepository<VisitLog, Integer>
{
    /**
     * Find the latest visit of a car that has not yet finished
     *
     * @param parkingId The parking id
     * @param carId     The car id
     * @return The VisitLog with exit time null that this car has made
     */
    @Query("SELECT v FROM VisitLog v WHERE v.parking.id = :parkingId AND v.car.id = :carId AND v.exitTime IS NULL")
    VisitLog findLatestVisitByCarId(@Param(value = "parkingId") int parkingId, @Param(value = "carId") int carId);
}
