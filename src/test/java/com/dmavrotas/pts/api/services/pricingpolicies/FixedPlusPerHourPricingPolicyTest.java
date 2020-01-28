package com.dmavrotas.pts.api.services.pricingpolicies;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FixedPlusPerHourPricingPolicyTest
{
    @Test
    void testClass()
    {
        var classUnderTest = new FixedPlusPerHourPricingPolicy(0.5f, 5.0f);

        var classUnderTestCopy = classUnderTest;

        assertEquals(classUnderTest, classUnderTestCopy);
        assertSame(classUnderTest, classUnderTestCopy);
        assertEquals(0.5f, classUnderTest.getPricePerHour());
        assertEquals(5.0f, classUnderTest.getFixedAmount());
        assertEquals(classUnderTest.toString(), classUnderTestCopy.toString());

        var classUnderTest2 = new FixedPlusPerHourPricingPolicy(0.7f, 6.0f);

        classUnderTest2.setPricePerHour(0.8f);
        classUnderTest2.setFixedAmount(7.0f);

        assertNotEquals(classUnderTest, classUnderTest2);
        assertNotEquals(classUnderTest, null);
        assertNotEquals(classUnderTest.toString(), classUnderTest2.toString());

        assertEquals(29.0f, classUnderTest.calculateParkingPrice(LocalDateTime.now(), LocalDateTime.now().plusDays(2)));
        assertThrows(IllegalArgumentException.class,
                     () -> classUnderTest.calculateParkingPrice(LocalDateTime.now(), null));
    }
}
