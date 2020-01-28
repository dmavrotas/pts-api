package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.PricingPolicy;
import com.dmavrotas.pts.api.services.PricingPolicyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/pts/pricingPolicy")
public class PricingPolicyController
{
    protected final PricingPolicyService pricingPolicyService;

    public PricingPolicyController(PricingPolicyService pricingPolicyService)
    {
        this.pricingPolicyService = pricingPolicyService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<PricingPolicy> get()
    {
        return pricingPolicyService.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public PricingPolicy get(@PathVariable(name = "id") int id)
    {
        return pricingPolicyService.getEntity(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public PricingPolicy save(@RequestBody PricingPolicy pricingPolicy)
    {
        return pricingPolicyService.saveEntity(pricingPolicy);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean delete(@PathVariable(name = "id") int id)
    {
        return pricingPolicyService.deleteEntity(pricingPolicyService.getEntity(id));
    }
}
