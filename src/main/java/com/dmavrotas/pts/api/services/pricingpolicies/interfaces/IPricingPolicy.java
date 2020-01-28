package com.dmavrotas.pts.api.services.pricingpolicies.interfaces;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IPricingPolicy extends Serializable
{
    float calculateParkingPrice(LocalDateTime entryTime, LocalDateTime exitTime);
}
