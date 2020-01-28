package com.dmavrotas.pts.api.services;

import com.dmavrotas.pts.api.models.PricingPolicy;
import com.dmavrotas.pts.api.repositories.IPricingPolicyRepository;
import com.dmavrotas.pts.api.services.interfaces.IService;
import org.springframework.stereotype.Component;

@Component
public class PricingPolicyService implements IService<PricingPolicy>
{
    protected final IPricingPolicyRepository pricingPolicyRepository;

    public PricingPolicyService(IPricingPolicyRepository pricingPolicyRepository)
    {
        this.pricingPolicyRepository = pricingPolicyRepository;
    }

    @Override
    public PricingPolicy getEntity(int id)
    {
        return pricingPolicyRepository.findById(id).orElse(null);
    }

    @Override
    public PricingPolicy saveEntity(PricingPolicy entity)
    {
        if (entity == null)
        {
            return null;
        }

        return pricingPolicyRepository.save(entity);
    }

    @Override
    public boolean deleteEntity(PricingPolicy entity)
    {
        if (entity == null)
        {
            return false;
        }

        pricingPolicyRepository.delete(entity);

        return pricingPolicyRepository.findById(entity.getId()).orElse(null) == null;
    }

    public Iterable<PricingPolicy> getAllEntities()
    {
        return pricingPolicyRepository.findAll();
    }
}
