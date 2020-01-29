package com.dmavrotas.pts.api.utils;

import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import com.dmavrotas.pts.api.services.pricingpolicies.PerHourPricingPolicy;
import com.dmavrotas.pts.api.services.pricingpolicies.interfaces.IPricingPolicy;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class PricingPolicyDeserializer extends StdDeserializer<IPricingPolicy>
{

    public PricingPolicyDeserializer()
    {
        this(null);
    }

    public PricingPolicyDeserializer(Class<?> vc)
    {
        super(vc);
    }

    @Override
    public IPricingPolicy deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException
    {
        JsonNode node = jp.getCodec().readTree(jp);

        if (node.has("fixedAmount"))
        {
            var fixedAmount = node.get("fixedAmount").floatValue();
            var pricePerHour = node.get("pricePerHour").floatValue();

            return new FixedPlusPerHourPricingPolicy(pricePerHour, fixedAmount);
        }
        else
        {
            var pricePerHour = node.get("pricePerHour").floatValue();

            return new PerHourPricingPolicy(pricePerHour);
        }
    }
}
