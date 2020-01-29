package com.dmavrotas.pts.api.services.pricingpolicies.interfaces;

import com.dmavrotas.pts.api.utils.PricingPolicyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonDeserialize(using = PricingPolicyDeserializer.class)
public interface IPricingPolicy extends Serializable
{
    float calculateParkingPrice(LocalDateTime entryTime, LocalDateTime exitTime);
}
