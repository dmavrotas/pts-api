package com.dmavrotas.pts.api.services;

import com.dmavrotas.pts.api.models.Parking;
import com.dmavrotas.pts.api.repositories.IParkingRepository;
import com.dmavrotas.pts.api.services.interfaces.IService;
import org.springframework.stereotype.Component;

@Component
public class ParkingService implements IService<Parking>
{
    protected final IParkingRepository parkingRepository;
    protected final PricingPolicyService pricingPolicyService;

    public ParkingService(IParkingRepository parkingRepository, PricingPolicyService pricingPolicyService)
    {
        this.parkingRepository = parkingRepository;
        this.pricingPolicyService = pricingPolicyService;
    }

    @Override
    public Parking getEntity(int id)
    {
        return parkingRepository.findById(id).orElse(null);
    }

    @Override
    public Parking saveEntity(Parking entity)
    {
        if (entity == null)
        {
            return null;
        }

        pricingPolicyService.saveEntity(entity.getPricingPolicy());
        return parkingRepository.save(entity);
    }

    @Override
    public boolean deleteEntity(Parking entity)
    {
        if (entity == null)
        {
            return false;
        }

        parkingRepository.delete(entity);

        return parkingRepository.findById(entity.getId()).orElse(null) == null;
    }

    public Iterable<Parking> getAllEntities()
    {
        return parkingRepository.findAll();
    }
}
