package com.dmavrotas.pts.api.exceptions;

public class ParkingNotFoundException extends RuntimeException
{
    public ParkingNotFoundException(String message)
    {
        super(message);
    }
}
