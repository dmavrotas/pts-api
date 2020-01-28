package com.dmavrotas.pts.api.services;

import com.dmavrotas.pts.api.models.*;
import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.models.enums.EPricingPolicy;
import com.dmavrotas.pts.api.repositories.RepositoryTestHelper;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class VisitLogServiceTest extends RepositoryTestHelper
{
    @Autowired
    private VisitLogService visitLogService;

    @Test
    void testService()
    {
        var pricingPolicy = new PricingPolicy();

        pricingPolicy.setName(EPricingPolicy.PER_HOUR);
        pricingPolicy.setFormula(new FixedPlusPerHourPricingPolicy(0.5f, 2.5f));
        pricingPolicy.setCreated(LocalDateTime.now());

        var parkingSlotType = new ParkingSlotType();

        parkingSlotType.setName(EParkingSlotType.STANDARD);
        parkingSlotType.setCreated(LocalDateTime.now());

        var parking = new Parking();

        parking.setName("Nice Massena");
        parking.setPricingPolicy(pricingPolicy);
        parking.setCreated(LocalDateTime.now());

        var car = new Car();

        car.setRegistrationPlate("EG-721-NF");
        car.setCreated(LocalDateTime.now());

        var car2 = new Car();

        car2.setRegistrationPlate("EG-725-NF");
        car2.setCreated(LocalDateTime.now());

        var parkingSlot = new ParkingSlot();

        parkingSlot.setFree(true);
        parkingSlot.setCar(car);
        parkingSlot.setParking(parking);
        parkingSlot.setParkingSlotType(parkingSlotType);
        parkingSlot.setCreated(LocalDateTime.now());

        var visitLog = new VisitLog();
        
        visitLog.setParking(parking);
        visitLog.setCar(car);
        visitLog.setEntryTime(LocalDateTime.now());

        var savedVisitLog = visitLogService.saveEntity(visitLog);

        assertNotNull(savedVisitLog);
        assertEquals(1, ((ArrayList) visitLogService.getAllEntities()).size());

        savedVisitLog.setExitTime(LocalDateTime.now());

        savedVisitLog = visitLogService.saveEntity(savedVisitLog);

        assertNotNull(savedVisitLog);
        assertEquals(1, ((ArrayList) visitLogService.getAllEntities()).size());
        assertNotNull(savedVisitLog.getExitTime());

        visitLog = new VisitLog();

        visitLog.setParking(parking);
        visitLog.setCar(car2);
        visitLog.setEntryTime(LocalDateTime.now());

        savedVisitLog = visitLogService.saveEntity(visitLog);

        assertNotNull(savedVisitLog);
        assertEquals(2, ((ArrayList) visitLogService.getAllEntities()).size());
        assertNull(savedVisitLog.getExitTime());

        assertNull(visitLogService.getEntity(-2));

        assertNotNull(visitLogService.getEntity(savedVisitLog.getId()));

        assertTrue(visitLogService.deleteEntity(savedVisitLog));
        assertEquals(1, ((ArrayList) visitLogService.getAllEntities()).size());

        assertFalse(visitLogService.deleteEntity(null));
        assertNull(visitLogService.saveEntity(null));
    }
}
