package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.PricingPolicy;
import com.dmavrotas.pts.api.models.enums.EPricingPolicy;
import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import com.dmavrotas.pts.api.services.pricingpolicies.PerHourPricingPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class PricingPolicyControllerTest extends BaseControllerTest
{
    @Test
    void testApi() throws Exception
    {
        var pricingPolicy = new PricingPolicy();

        pricingPolicy.setId(1);
        pricingPolicy.setName(EPricingPolicy.PER_HOUR);
        pricingPolicy.setFormula(new PerHourPricingPolicy(0.5f));
        pricingPolicy.setCreated(LocalDateTime.now());

        var savedPricingPolicy = pricingPolicyRepository.save(pricingPolicy);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pts/pricingPolicy/1", MockMvcResultMatchers.status().isOk(),
                                                   savedPricingPolicy))
               .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/pts/pricingPolicy", MockMvcResultMatchers.status().isOk(),
                                           List.of(savedPricingPolicy)))
               .andExpect(MockMvcResultMatchers.status().isOk());

        pricingPolicy = new PricingPolicy();

        pricingPolicy.setId(2);
        pricingPolicy.setName(EPricingPolicy.FIXED_AMOUNT_PLUS_PER_HOUR);
        pricingPolicy.setFormula(new FixedPlusPerHourPricingPolicy(0.5f, 2.0f));
        pricingPolicy.setCreated(LocalDateTime.now());

        var savedPricingPolicy2 = pricingPolicyRepository.save(pricingPolicy);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pts/pricingPolicy", MockMvcResultMatchers.status().isOk(),
                                                   List.of(savedPricingPolicy, savedPricingPolicy2))).andExpect(
                MockMvcResultMatchers.status().isOk());

        pricingPolicy = new PricingPolicy();

        pricingPolicy.setId(3);
        pricingPolicy.setName(EPricingPolicy.PER_HOUR);
        pricingPolicy.setFormula(new PerHourPricingPolicy(3.5f));
        pricingPolicy.setCreated(LocalDateTime.now());

        var postContent = post("/api/pts/pricingPolicy")
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .content(objectMapper.writeValueAsString(pricingPolicy));

        mockMvc.perform(postContent).andExpect(MockMvcResultMatchers.status().isOk());

        var savedPricingPolicy4 = pricingPolicyRepository.findById(3);

        assertNotNull(savedPricingPolicy4);

        mockMvc.perform(delete("/api/pts/pricingPolicy/3")).andExpect(MockMvcResultMatchers.status().isOk());

        var deletedPricingPolicy = pricingPolicyRepository.findById(3).orElse(null);

        assertNull(deletedPricingPolicy);
    }
}
