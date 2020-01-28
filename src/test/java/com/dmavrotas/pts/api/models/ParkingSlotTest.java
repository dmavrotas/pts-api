package com.dmavrotas.pts.api.models;

import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSlotTest
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

        var parkingSlot = new ParkingSlot();

        parkingSlot.setId(1);
        parkingSlot.setFree(true);
        parkingSlot.setParking(parking);
        parkingSlot.setParkingSlotType(parkingSlotType);
        parkingSlot.setCreated(LocalDateTime.now());

        assertEquals(1, parkingSlot.getId());
        assertTrue(parkingSlot.isFree());
        assertEquals(parkingSlotType, parkingSlot.getParkingSlotType());
        assertEquals(parking, parkingSlot.getParking());
        assertNotEquals(LocalDateTime.now(), parkingSlot.getCreated());

        var parkingSlotCopy = parkingSlot;

        assertSame(parkingSlot, parkingSlotCopy);
        assertEquals(parkingSlot, parkingSlotCopy);
        assertNotEquals(parkingSlot, null);
        assertEquals(parkingSlot.toString(), parkingSlotCopy.toString());

        var parkingSlot2 = new ParkingSlot();

        parkingSlot2.setId(2);
        parkingSlot2.setParking(parking);
        parkingSlot2.setParkingSlotType(parkingSlotType);
        parkingSlot2.setFree(false);
        parkingSlot2.setCreated(LocalDateTime.now());

        assertNotEquals(parkingSlot, parkingSlot2);
        assertNotEquals(parkingSlot.toString(), parkingSlot2.toString());
    }
}
