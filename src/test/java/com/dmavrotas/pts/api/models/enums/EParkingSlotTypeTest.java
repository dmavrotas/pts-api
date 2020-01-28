package com.dmavrotas.pts.api.models.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EParkingSlotTypeTest
{
    @Test
    void testLength()
    {
        assertEquals(3, EParkingSlotType.values().length);
    }

    @Test
    void testValues()
    {
        assertEquals(EParkingSlotType.STANDARD, EParkingSlotType.valueOf("STANDARD"));
        assertEquals(EParkingSlotType.LOW_ELECTRICAL_POWER, EParkingSlotType.valueOf("LOW_ELECTRICAL_POWER"));
        assertEquals(EParkingSlotType.HIGH_ELECTRICAL_POWER, EParkingSlotType.valueOf("HIGH_ELECTRICAL_POWER"));
    }
}
