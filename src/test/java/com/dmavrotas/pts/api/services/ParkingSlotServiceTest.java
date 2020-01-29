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

public class ParkingSlotServiceTest extends RepositoryTestHelper
{
    @Autowired
    private ParkingSlotService parkingSlotService;

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

        car2.setRegistrationPlate("EG-322-NF");
        car2.setCreated(LocalDateTime.now());

        var parkingSlot = new ParkingSlot();

        parkingSlot.setFree(true);
        parkingSlot.setCar(car);
        parkingSlot.setParking(parking);
        parkingSlot.setParkingSlotType(parkingSlotType);
        parkingSlot.setCreated(LocalDateTime.now());

        var savedParkingSlot = parkingSlotService.saveEntity(parkingSlot);

        assertNotNull(savedParkingSlot);
        assertEquals(1, ((ArrayList) parkingSlotService.getAllEntities()).size());

        savedParkingSlot.setFree(false);

        savedParkingSlot = parkingSlotService.saveEntity(savedParkingSlot);

        assertNotNull(savedParkingSlot);
        assertEquals(1, ((ArrayList) parkingSlotService.getAllEntities()).size());
        assertFalse(savedParkingSlot.isFree());

        parkingSlot = new ParkingSlot();

        parkingSlot.setFree(false);
        parkingSlot.setCar(car2);
        parkingSlot.setParking(parking);
        parkingSlot.setParkingSlotType(parkingSlotType);
        parkingSlot.setCreated(LocalDateTime.now());

        savedParkingSlot = parkingSlotService.saveEntity(parkingSlot);

        assertNotNull(savedParkingSlot);
        assertEquals(2, ((ArrayList) parkingSlotService.getAllEntities()).size());
        assertEquals("EG-322-NF", savedParkingSlot.getCar().getRegistrationPlate());

        assertNull(parkingSlotService.getEntity(-2));

        assertNotNull(parkingSlotService.getEntity(savedParkingSlot.getId()));

        assertTrue(parkingSlotService.deleteEntity(savedParkingSlot));
        assertEquals(1, ((ArrayList) parkingSlotService.getAllEntities()).size());

        assertFalse(parkingSlotService.deleteEntity(null));
        assertNull(parkingSlotService.saveEntity(null));
    }

    @Test
    void findFreeSlotsForType()
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

        car2.setRegistrationPlate("EG-322-NF");
        car2.setCreated(LocalDateTime.now());

        var parkingSlot = new ParkingSlot();

        parkingSlot.setFree(false);
        parkingSlot.setCar(car);
        parkingSlot.setParking(parking);
        parkingSlot.setParkingSlotType(parkingSlotType);
        parkingSlot.setCreated(LocalDateTime.now());

        parkingSlotService.saveEntity(parkingSlot);

        parkingSlot = new ParkingSlot();

        parkingSlot.setFree(true);
        parkingSlot.setCar(null);
        parkingSlot.setParking(parking);
        parkingSlot.setParkingSlotType(parkingSlotType);
        parkingSlot.setCreated(LocalDateTime.now());

        var savedParkingSlot = parkingSlotService.saveEntity(parkingSlot);

        var savedParking = (Parking) ((ArrayList) parkingRepository.findAll()).get(0);
        var savedParkingSlotType = (ParkingSlotType) ((ArrayList) parkingSlotTypeRepository.findAll()).get(0);

        assertEquals(2, ((ArrayList) parkingSlotRepository.findAll()).size());
        assertEquals(savedParkingSlot,
                     parkingSlotService.findFreeSlotsForType(savedParking.getId(), savedParkingSlotType.getId()));

        savedParkingSlot.setCar(car2);
        savedParkingSlot.setFree(false);

        parkingSlotService.saveEntity(savedParkingSlot);

        assertNull(parkingSlotService.findFreeSlotsForType(0, 0));

        assertEquals(savedParkingSlot, parkingSlotService.findParkingSlotByCarId(car2.getId()));
    }
}
