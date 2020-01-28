package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.*;
import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.models.enums.EPricingPolicy;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSlotRepositoryTest extends RepositoryTestHelper
{
    @Test
    void repositoryTest()
    {
        assertEquals(0, ((ArrayList)parkingSlotRepository.findAll()).size());

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
        parkingSlot.setParkingSlotType(savedParkingSlotType);
        parkingSlot.setCar(car);
        parkingSlot.setCreated(LocalDateTime.now());

        var savedParkingSlot = parkingSlotRepository.save(parkingSlot);

        assertNotNull(savedParkingSlot);
        assertEquals(savedParking, savedParkingSlot.getParking());
        assertEquals(savedCar, savedParkingSlot.getCar());
        assertEquals(savedParkingSlotType, savedParkingSlot.getParkingSlotType());

        parkingSlot = new ParkingSlot();
        parkingSlot.setCar(null);
        parkingSlot.setFree(true);
        parkingSlot.setParkingSlotType(parkingSlotType);
        parkingSlot.setParking(parking);
        parkingSlot.setCreated(LocalDateTime.now());

        savedParkingSlot = parkingSlotRepository.save(parkingSlot);

        assertNotNull(savedParkingSlot);
        assertEquals(savedParking, savedParkingSlot.getParking());
        assertEquals(savedParkingSlotType, savedParkingSlot.getParkingSlotType());
        assertNull(savedParkingSlot.getCar());
    }

    @Test
    void testFindFreeSlotsForType()
    {
        assertEquals(0, ((ArrayList)parkingSlotRepository.findAll()).size());

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

        parkingSlotType = new ParkingSlotType();

        parkingSlotType.setId(2);
        parkingSlotType.setName(EParkingSlotType.HIGH_ELECTRICAL_POWER);
        parkingSlotType.setCreated(LocalDateTime.now());

        var savedParkingSlotType2 = parkingSlotTypeRepository.save(parkingSlotType);

        parkingSlotType = new ParkingSlotType();

        parkingSlotType.setId(3);
        parkingSlotType.setName(EParkingSlotType.LOW_ELECTRICAL_POWER);
        parkingSlotType.setCreated(LocalDateTime.now());

        var savedParkingSlotType3 = parkingSlotTypeRepository.save(parkingSlotType);

        var parking = new Parking();

        parking.setId(1);
        parking.setName("Nice Massena");
        parking.setPricingPolicy(savedPricingPolicy);
        parking.setCreated(LocalDateTime.now());

        var savedParking = parkingRepository.save(parking);

        var car = new Car();

        car.setId(1);
        car.setRegistrationPlate("EG-721-NF");
        car.setCreated(LocalDateTime.now());

        var savedCar = carRepository.save(car);

        var parkingSlot = new ParkingSlot();

        parkingSlot.setId(1);
        parkingSlot.setParking(savedParking);
        parkingSlot.setParkingSlotType(savedParkingSlotType);
        parkingSlot.setCar(car);
        parkingSlot.setFree(false);
        parkingSlot.setCreated(LocalDateTime.now());

        parkingSlotRepository.save(parkingSlot);

        parkingSlot = new ParkingSlot();

        parkingSlot.setId(2);
        parkingSlot.setParking(savedParking);
        parkingSlot.setParkingSlotType(savedParkingSlotType);
        parkingSlot.setFree(true);
        parkingSlot.setCreated(LocalDateTime.now());

        parkingSlotRepository.save(parkingSlot);

        parkingSlot = new ParkingSlot();

        parkingSlot.setId(3);
        parkingSlot.setParking(savedParking);
        parkingSlot.setParkingSlotType(savedParkingSlotType2);
        parkingSlot.setFree(true);
        parkingSlot.setCreated(LocalDateTime.now());

        parkingSlotRepository.save(parkingSlot);

        parkingSlot = new ParkingSlot();

        parkingSlot.setId(4);
        parkingSlot.setParking(savedParking);
        parkingSlot.setParkingSlotType(savedParkingSlotType3);
        parkingSlot.setFree(true);
        parkingSlot.setCreated(LocalDateTime.now());

        parkingSlotRepository.save(parkingSlot);

        var results = parkingSlotRepository.findFreeSlotsForType(parking.getId(), savedParkingSlotType.getId());

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(2, results.get(0).getId());

        parkingSlot = new ParkingSlot();

        parkingSlot.setId(2);
        parkingSlot.setParking(savedParking);
        parkingSlot.setParkingSlotType(savedParkingSlotType);
        parkingSlot.setCar(car);
        parkingSlot.setFree(false);
        parkingSlot.setCreated(LocalDateTime.now());

        parkingSlotRepository.save(parkingSlot);

        assertEquals(4, ((ArrayList)parkingSlotRepository.findAll()).size());

        results = parkingSlotRepository.findFreeSlotsForType(parking.getId(), savedParkingSlotType.getId());

        assertNotNull(results);
        assertEquals(0, results.size());
    }
}
