package com.dmavrotas.pts.api.models;

import com.dmavrotas.pts.api.models.enums.EPricingPolicy;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PricingPolicyTest
{
    @Test
    void test()
    {
        var pricingPolicy = new PricingPolicy();

        pricingPolicy.setId(1);
        pricingPolicy.setName(EPricingPolicy.PER_HOUR);
        pricingPolicy.setFormula(new FixedPlusPerHourPricingPolicy(0.5f, 2.5f));
        pricingPolicy.setCreated(LocalDateTime.now());

        assertEquals(1, pricingPolicy.getId());
        assertEquals(EPricingPolicy.PER_HOUR, pricingPolicy.getName());
        assertEquals(new FixedPlusPerHourPricingPolicy(0.5f, 2.5f), pricingPolicy.getFormula());
        assertNotEquals(LocalDateTime.now(), pricingPolicy.getCreated());

        var pricingPolicyCopy = pricingPolicy;

        assertSame(pricingPolicy, pricingPolicyCopy);
        assertEquals(pricingPolicy, pricingPolicyCopy);
        assertNotEquals(pricingPolicy, null);
        assertEquals(pricingPolicy.toString(), pricingPolicyCopy.toString());

        var pricingPolicy2 = new PricingPolicy();

        pricingPolicy2.setId(2);
        pricingPolicy2.setName(EPricingPolicy.FIXED_AMOUNT_PLUS_PER_HOUR);
        pricingPolicy2.setFormula(new FixedPlusPerHourPricingPolicy(0.8f, 2.6f));
        pricingPolicy2.setCreated(LocalDateTime.now());

        assertNotEquals(pricingPolicy, pricingPolicy2);
        assertNotEquals(pricingPolicy.toString(), pricingPolicy2.toString());
    }
}
