package com.dmavrotas.pts.api.services;

import com.dmavrotas.pts.api.models.Parking;
import com.dmavrotas.pts.api.models.ParkingSlotType;
import com.dmavrotas.pts.api.models.PricingPolicy;
import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.models.enums.EPricingPolicy;
import com.dmavrotas.pts.api.repositories.RepositoryTestHelper;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import com.dmavrotas.pts.api.services.pricingpolicies.PerHourPricingPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingServiceTest extends RepositoryTestHelper
{
    @Autowired
    private ParkingService parkingService;

    @Test
    void testService()
    {
        var pricingPolicy = new PricingPolicy();

        pricingPolicy.setName(EPricingPolicy.PER_HOUR);
        pricingPolicy.setFormula(new PerHourPricingPolicy(2.5f));
        pricingPolicy.setCreated(LocalDateTime.now());

        var pricingPolicy2 = new PricingPolicy();

        pricingPolicy2.setName(EPricingPolicy.FIXED_AMOUNT_PLUS_PER_HOUR);
        pricingPolicy2.setFormula(new FixedPlusPerHourPricingPolicy(0.5f, 2.5f));
        pricingPolicy2.setCreated(LocalDateTime.now());

        var parkingSlotType = new ParkingSlotType();

        parkingSlotType.setName(EParkingSlotType.STANDARD);
        parkingSlotType.setCreated(LocalDateTime.now());

        var parking = new Parking();

        parking.setName("Nice Massena");
        parking.setPricingPolicy(pricingPolicy);
        parking.setCreated(LocalDateTime.now());

        var savedParking = parkingService.saveEntity(parking);

        assertNotNull(savedParking);
        assertEquals(1, ((ArrayList) parkingService.getAllEntities()).size());

        savedParking.setName("Greek Parking");

        savedParking = parkingService.saveEntity(savedParking);

        assertNotNull(savedParking);
        assertEquals(1, ((ArrayList) parkingService.getAllEntities()).size());
        assertEquals("Greek Parking", savedParking.getName());

        parking = new Parking();

        parking.setName("Nice Massena");
        parking.setPricingPolicy(pricingPolicy2);
        parking.setCreated(LocalDateTime.now());

        savedParking = parkingService.saveEntity(parking);

        assertNotNull(savedParking);
        assertEquals(2, ((ArrayList) parkingService.getAllEntities()).size());
        assertEquals(pricingPolicy2, savedParking.getPricingPolicy());

        assertNull(parkingService.getEntity(-2));

        assertNotNull(parkingService.getEntity(savedParking.getId()));

        assertTrue(parkingService.deleteEntity(savedParking));
        assertEquals(1, ((ArrayList) parkingService.getAllEntities()).size());

        assertFalse(parkingService.deleteEntity(null));
        assertNull(parkingService.saveEntity(null));
    }
}
