package com.dmavrotas.pts.api.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingSlotTypeNotFoundExceptionTest
{
    @Test
    void test()
    {
        var exception = new ParkingSlotTypeNotFound("message");

        assertEquals("message", exception.getMessage());
    }
}
