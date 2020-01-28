package com.dmavrotas.pts.api.services;

import com.dmavrotas.pts.api.models.PricingPolicy;
import com.dmavrotas.pts.api.models.enums.EPricingPolicy;
import com.dmavrotas.pts.api.repositories.RepositoryTestHelper;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import com.dmavrotas.pts.api.services.pricingpolicies.PerHourPricingPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PricingPolicyServiceTest extends RepositoryTestHelper
{
    @Autowired
    private PricingPolicyService pricingPolicyService;

    @Test
    void testService()
    {
        var pricingPolicy = new PricingPolicy();

        pricingPolicy.setName(EPricingPolicy.PER_HOUR);
        pricingPolicy.setFormula(new PerHourPricingPolicy(0.5f));
        pricingPolicy.setCreated(LocalDateTime.now());

        var savedPricingPolicy = pricingPolicyService.saveEntity(pricingPolicy);

        assertNotNull(savedPricingPolicy);
        assertEquals(1, ((ArrayList) pricingPolicyService.getAllEntities()).size());

        savedPricingPolicy.setName(EPricingPolicy.FIXED_AMOUNT_PLUS_PER_HOUR);
        savedPricingPolicy.setFormula(new FixedPlusPerHourPricingPolicy(0.5f, 2.5f));

        savedPricingPolicy = pricingPolicyService.saveEntity(savedPricingPolicy);

        assertNotNull(savedPricingPolicy);
        assertEquals(1, ((ArrayList) pricingPolicyService.getAllEntities()).size());
        assertEquals(EPricingPolicy.FIXED_AMOUNT_PLUS_PER_HOUR, savedPricingPolicy.getName());

        pricingPolicy = new PricingPolicy();

        pricingPolicy.setName(EPricingPolicy.PER_HOUR);
        pricingPolicy.setFormula(new PerHourPricingPolicy(0.5f));
        pricingPolicy.setCreated(LocalDateTime.now());

        savedPricingPolicy = pricingPolicyService.saveEntity(pricingPolicy);

        assertNotNull(savedPricingPolicy);
        assertEquals(2, ((ArrayList) pricingPolicyService.getAllEntities()).size());
        assertEquals(new PerHourPricingPolicy(0.5f), savedPricingPolicy.getFormula());
        assertEquals(EPricingPolicy.PER_HOUR, savedPricingPolicy.getName());

        assertNull(pricingPolicyService.getEntity(-2));

        assertNotNull(pricingPolicyService.getEntity(savedPricingPolicy.getId()));

        assertTrue(pricingPolicyService.deleteEntity(savedPricingPolicy));
        assertEquals(1, ((ArrayList) pricingPolicyService.getAllEntities()).size());

        assertFalse(pricingPolicyService.deleteEntity(null));
        assertNull(pricingPolicyService.saveEntity(null));
    }
}
