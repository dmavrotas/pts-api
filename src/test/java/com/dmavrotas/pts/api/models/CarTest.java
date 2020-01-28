package com.dmavrotas.pts.api.models;

import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CarTest
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
        parkingSlot.setParkingSlotType(parkingSlotType);
        parkingSlot.setCar(car);
        parkingSlot.setCreated(LocalDateTime.now());

        assertEquals(1, car.getId());
        assertEquals("EG-721-NF", car.getRegistrationPlate());
        assertNotEquals(LocalDateTime.now(), car.getCreated());

        var carCopy = car;

        assertSame(car, carCopy);
        assertEquals(car, carCopy);
        assertNotEquals(car, null);
        assertEquals(car.toString(), carCopy.toString());

        var car2 = new Car();

        car2.setId(2);
        car2.setRegistrationPlate("EG-722-NF");
        car2.setCreated(LocalDateTime.now());

        assertNotEquals(car, car2);
        assertNotEquals(car.toString(), car2.toString());
    }
}
