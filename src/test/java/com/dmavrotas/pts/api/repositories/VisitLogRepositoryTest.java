package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.*;
import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.models.enums.EPricingPolicy;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VisitLogRepositoryTest extends RepositoryTestHelper
{
    @Test
    void testRepository()
    {
        assertEquals(0, ((ArrayList)visitLogRepository.findAll()).size());

        var pricingPolicy = new PricingPolicy();

        pricingPolicy.setId(1);
        pricingPolicy.setName(EPricingPolicy.PER_HOUR);
        pricingPolicy.setFormula(new FixedPlusPerHourPricingPolicy(0.5f, 2.5f));
        pricingPolicy.setCreated(LocalDateTime.now());

        var savedPricingPolicy = pricingPolicyRepository.save(pricingPolicy);

        var parkingSlotType = new ParkingSlotType();

        parkingSlotType.setId(1);
        parkingSlotType.setName(EParkingSlotType.STANDARD);
        parkingSlotType.setCreated(LocalDateTime.now());

        var savedParkingSlotType = parkingSlotTypeRepository.save(parkingSlotType);

        var parking = new Parking();

        parking.setId(1);
        parking.setName("Nice Massena");
        parking.setPricingPolicy(pricingPolicy);
        parking.setCreated(LocalDateTime.now());

        var savedParking = parkingRepository.save(parking);

        var car = new Car();

        car.setId(1);
        car.setRegistrationPlate("EG-721-NF");
        car.setCreated(LocalDateTime.now());

        var savedCar = carRepository.save(car);

        var parkingSlot = new ParkingSlot();

        parkingSlot.setId(1);
        parkingSlot.setFree(true);
        parkingSlot.setParking(parking);
        parkingSlot.setCar(car);
        parkingSlot.setParkingSlotType(savedParkingSlotType);
        parkingSlot.setCreated(LocalDateTime.now());

        var savedParkingSlot = parkingSlotRepository.save(parkingSlot);

        var visitLog = new VisitLog();

        visitLog.setId(1);
        visitLog.setCar(car);
        visitLog.setParking(parking);
        visitLog.setEntryTime(LocalDateTime.now());
        visitLog.setExitTime(LocalDateTime.now().plusDays(1));

        var savedVisitLog = visitLogRepository.save(visitLog);

        assertNotNull(visitLog);
        assertEquals(savedCar, savedVisitLog.getCar());
        assertEquals(savedParking, savedVisitLog.getParking());
    }
}
