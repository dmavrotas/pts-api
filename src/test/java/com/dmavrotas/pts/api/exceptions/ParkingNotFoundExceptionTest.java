package com.dmavrotas.pts.api.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingNotFoundExceptionTest
{
    @Test
    void test()
    {
        var exception = new ParkingNotFoundException("message");

        assertEquals("message", exception.getMessage());
    }
}
