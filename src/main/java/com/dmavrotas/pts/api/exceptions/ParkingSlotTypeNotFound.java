package com.dmavrotas.pts.api.exceptions;

public class ParkingSlotTypeNotFound extends RuntimeException
{
    public ParkingSlotTypeNotFound(String message)
    {
        super(message);
    }
}
