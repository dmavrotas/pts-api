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

class BillServiceTest extends RepositoryTestHelper
{
    @Autowired
    private BillService billService;

    @Test
    void testService()
    {
        var pricingPolicy = new PricingPolicy();

        pricingPolicy.setName(EPricingPolicy.FIXED_AMOUNT_PLUS_PER_HOUR);
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

        var parkingSlot = new ParkingSlot();

        parkingSlot.setFree(true);
        parkingSlot.setCar(car);
        parkingSlot.setParking(parking);
        parkingSlot.setParkingSlotType(parkingSlotType);
        parkingSlot.setCreated(LocalDateTime.now());

        var visitLog = new VisitLog();

        visitLog.setCar(car);
        visitLog.setParking(parking);
        visitLog.setCar(car);
        visitLog.setEntryTime(LocalDateTime.now());
        visitLog.setExitTime(LocalDateTime.now().plusDays(1));

        var bill = new Bill();

        bill.setParkingSlot(parkingSlot);
        bill.setVisitLog(visitLog);
        bill.setAmount(2.0);
        bill.setCreated(LocalDateTime.now());

        var savedBill = billService.saveEntity(bill);

        assertNotNull(savedBill);
        assertEquals(1, ((ArrayList) billService.getAllEntities()).size());

        savedBill.setAmount(5.0);

        savedBill = billService.saveEntity(savedBill);

        assertNotNull(savedBill);
        assertEquals(1, ((ArrayList) billService.getAllEntities()).size());
        assertEquals(5.0, savedBill.getAmount());

        bill = new Bill();

        bill.setParkingSlot(parkingSlot);
        bill.setVisitLog(visitLog);
        bill.setAmount(2.0);
        bill.setCreated(LocalDateTime.now());

        savedBill = billService.saveEntity(bill);

        assertNotNull(savedBill);
        assertEquals(2, ((ArrayList) billService.getAllEntities()).size());
        assertEquals(2.0, savedBill.getAmount());

        assertNull(billService.getEntity(-2));

        assertNotNull(billService.getEntity(savedBill.getId()));

        assertTrue(billService.deleteEntity(savedBill));
        assertEquals(1, ((ArrayList) billService.getAllEntities()).size());

        assertFalse(billService.deleteEntity(null));
        assertNull(billService.saveEntity(null));

        var realBill = billService.createPaymentFromVisitLogAndParkingSlot(parking, parkingSlot, visitLog);

        assertNotNull(realBill);
        assertEquals(14.5, realBill.getAmount());
    }
}
