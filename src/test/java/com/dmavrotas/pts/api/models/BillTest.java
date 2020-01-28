package com.dmavrotas.pts.api.models;

import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BillTest
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
        visitLog.setEntryTime(LocalDateTime.now());
        visitLog.setExitTime(LocalDateTime.now().plusDays(1));

        var bill = new Bill();

        bill.setId(1);
        bill.setParkingSlot(parkingSlot);
        bill.setVisitLog(visitLog);
        bill.setAmount(2.0);
        bill.setCreated(LocalDateTime.now());

        assertEquals(1, bill.getId());
        assertEquals(parkingSlot, bill.getParkingSlot());
        assertEquals(visitLog, bill.getVisitLog());
        assertEquals(2.0, bill.getAmount());
        assertNotEquals(LocalDateTime.now(), bill.getCreated());

        var billCopy = bill;

        assertSame(bill, billCopy);
        assertEquals(bill, billCopy);
        assertNotEquals(bill, null);
        assertEquals(bill.toString(), billCopy.toString());

        var bill2 = new Bill();

        bill2.setId(2);
        bill2.setParkingSlot(parkingSlot);
        bill2.setVisitLog(visitLog);

        bill2.setCreated(LocalDateTime.now());
        bill.setAmount(2.5);
        assertNotEquals(bill, bill2);
        assertNotEquals(bill.toString(), bill2.toString());
    }
}
