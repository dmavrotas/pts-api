package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.PricingPolicy;
import com.dmavrotas.pts.api.models.enums.EPricingPolicy;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PricingPolicyRepositoryTest extends RepositoryTestHelper
{
    @Test
    void testRepository()
    {
        assertEquals(0, ((ArrayList)pricingPolicyRepository.findAll()).size());

        var pricingPolicy = new PricingPolicy();

        pricingPolicy.setId(1);
        pricingPolicy.setName(EPricingPolicy.PER_HOUR);
        pricingPolicy.setFormula(new FixedPlusPerHourPricingPolicy(0.5f, 2.5f));
        pricingPolicy.setCreated(LocalDateTime.now());

        var savedPricingPolicy = pricingPolicyRepository.save(pricingPolicy);

        assertNotNull(savedPricingPolicy);
        assertEquals(EPricingPolicy.PER_HOUR, savedPricingPolicy.getName());
        assertEquals(new FixedPlusPerHourPricingPolicy(0.5f, 2.5f), savedPricingPolicy.getFormula());
    }
}
