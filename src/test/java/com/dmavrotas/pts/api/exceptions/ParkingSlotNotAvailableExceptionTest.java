package com.dmavrotas.pts.api.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingSlotNotAvailableExceptionTest
{
    @Test
    void test()
    {
        var exception = new ParkingSlotNotAvailableException("message");

        assertEquals("message", exception.getMessage());
    }
}
