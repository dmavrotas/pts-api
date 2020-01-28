package com.dmavrotas.pts.api.models;

import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VisitLogTest
{
    @Test
    void test()
    {
        var pricingPolicy = new PricingPolicy();

        pricingPolicy.setId(1);
        pricingPolicy.setFormula(new FixedPlusPerHourPricingPolicy(0.5f, 2.5f));
        pricingPolicy.setCreated(LocalDateTime.now());

        var parkingSlotType = new ParkingSlotType();

        parkingSlotType.setId(1);
        parkingSlotType.setName(EParkingSlotType.STANDARD);
        parkingSlotType.setCreated(LocalDateTime.now());

        var parking = new Parking();

        parking.setId(1);
        parking.setName("Nice Massena");
        parking.setPricingPolicy(pricingPolicy);
        parking.setCreated(LocalDateTime.now());

        var car = new Car();

        car.setId(1);
        car.setRegistrationPlate("EG-721-NF");
        car.setCreated(LocalDateTime.now());

        var parkingSlot = new ParkingSlot();

        parkingSlot.setId(1);
        parkingSlot.setFree(true);
        parkingSlot.setCar(car);
        parkingSlot.setParkingSlotType(parkingSlotType);
        parkingSlot.setCreated(LocalDateTime.now());

        var visitLog = new VisitLog();

        visitLog.setId(1);
        visitLog.setCar(car);
        visitLog.setParking(parking);
        visitLog.setEntryTime(LocalDateTime.now());
        visitLog.setExitTime(LocalDateTime.now().plusDays(1));

        assertEquals(1, visitLog.getId());
        assertEquals(car, visitLog.getCar());
        assertEquals(parking, visitLog.getParking());
        assertNotEquals(LocalDateTime.now(), visitLog.getEntryTime());
        assertNotEquals(LocalDateTime.now().plusDays(1), visitLog.getExitTime());

        var visitLogCopy = visitLog;

        assertSame(visitLog, visitLogCopy);
        assertEquals(visitLog, visitLogCopy);
        assertNotEquals(visitLog, null);
        assertEquals(visitLog.toString(), visitLogCopy.toString());

        var visitLog2 = new VisitLog();

        visitLog2.setId(2);
        visitLog2.setCar(car);
        visitLog2.setParking(parking);
        visitLog2.setEntryTime(LocalDateTime.now());
        visitLog2.setExitTime(LocalDateTime.now().plusHours(2));

        assertNotEquals(visitLog, visitLog2);
        assertNotEquals(visitLog.toString(), visitLog2.toString());
    }
}
