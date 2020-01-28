package com.dmavrotas.pts.api.exceptions;

public class CarNotParkedException extends RuntimeException
{
    public CarNotParkedException(String message)
    {
        super(message);
    }
}
