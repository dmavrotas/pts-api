package com.dmavrotas.pts.api.exceptions;

public class ParkingSlotNotAvailableException extends RuntimeException
{
    public ParkingSlotNotAvailableException(String message)
    {
        super(message);
    }
}
