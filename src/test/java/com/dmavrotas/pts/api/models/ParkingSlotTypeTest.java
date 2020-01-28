package com.dmavrotas.pts.api.models;

import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSlotTypeTest
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

        assertEquals(1, parkingSlotType.getId());
        assertEquals(EParkingSlotType.STANDARD, parkingSlotType.getName());
        assertNotEquals(LocalDateTime.now(), parkingSlotType.getCreated());

        var parkingSlotTypeCopy = parkingSlotType;

        assertSame(parkingSlotType, parkingSlotTypeCopy);
        assertEquals(parkingSlotType, parkingSlotTypeCopy);
        assertNotEquals(parkingSlotType, null);
        assertEquals(parkingSlotType.toString(), parkingSlotTypeCopy.toString());

        var parkingSlotType2 = new ParkingSlotType();

        parkingSlotType2.setId(2);
        parkingSlotType2.setName(EParkingSlotType.HIGH_ELECTRICAL_POWER);
        parkingSlotType2.setCreated(LocalDateTime.now());

        assertNotEquals(parkingSlotType, parkingSlotType2);
        assertNotEquals(parkingSlotType.toString(), parkingSlotType2.toString());
    }
}
