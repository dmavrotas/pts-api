package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.PricingPolicy;
import org.springframework.data.repository.CrudRepository;

public interface IPricingPolicyRepository extends CrudRepository<PricingPolicy, Integer>
{
}
