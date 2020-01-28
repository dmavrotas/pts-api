package com.dmavrotas.pts.api.services.pricingpolicies;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PerHourPricingPolicyTest
{
    @Test
    void testClass()
    {
        var classUnderTest = new PerHourPricingPolicy(0.5f);

        var classUnderTestCopy = classUnderTest;

        assertEquals(classUnderTest, classUnderTestCopy);
        assertSame(classUnderTest, classUnderTestCopy);
        assertEquals(0.5f, classUnderTest.getPricePerHour());
        assertEquals(classUnderTest.toString(), classUnderTestCopy.toString());

        var classUnderTest2 = new PerHourPricingPolicy(0.7f);

        classUnderTest2.setPricePerHour(0.8f);

        assertNotEquals(classUnderTest, classUnderTest2);
        assertNotEquals(classUnderTest, null);
        assertNotEquals(classUnderTest.toString(), classUnderTest2.toString());

        assertEquals(24.0f, classUnderTest.calculateParkingPrice(LocalDateTime.now(), LocalDateTime.now().plusDays(2)));
        assertThrows(IllegalArgumentException.class,
                     () -> classUnderTest.calculateParkingPrice(LocalDateTime.now(), null));
    }
}
