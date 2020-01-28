package com.dmavrotas.pts.api.models.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EPricingPolicyTest
{
    @Test
    void testLength()
    {
        assertEquals(2, EPricingPolicy.values().length);
    }

    @Test
    void testValues()
    {
        assertEquals(EPricingPolicy.PER_HOUR, EPricingPolicy.valueOf("PER_HOUR"));
        assertEquals(EPricingPolicy.FIXED_AMOUNT_PLUS_PER_HOUR, EPricingPolicy.valueOf("FIXED_AMOUNT_PLUS_PER_HOUR"));
    }
}
