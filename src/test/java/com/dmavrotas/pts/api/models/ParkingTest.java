package com.dmavrotas.pts.api.models;

import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ParkingTest
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

        assertEquals(1, parking.getId());
        assertEquals("Nice Massena", parking.getName());
        assertEquals(pricingPolicy, parking.getPricingPolicy());
        assertNotEquals(LocalDateTime.now(), parking.getCreated());

        var parkingCopy = parking;

        assertSame(parking, parkingCopy);
        assertEquals(parking, parkingCopy);
        assertNotEquals(parking, null);
        assertEquals(parking.toString(), parkingCopy.toString());

        var parking2 = new Parking();

        parking2.setId(2);
        parking2.setName("Gambetta");
        parking2.setPricingPolicy(pricingPolicy);
        parking2.setCreated(LocalDateTime.now());

        assertNotEquals(parking, parking2);
        assertNotEquals(parking.toString(), parking2.toString());
    }
}
