package com.dmavrotas.pts.api.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarNotParkedExceptionTest
{
    @Test
    void test()
    {
        var exception = new CarNotParkedException("message");

        assertEquals("message", exception.getMessage());
    }
}
