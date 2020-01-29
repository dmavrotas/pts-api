package com.dmavrotas.pts.api.services;

import com.dmavrotas.pts.api.models.ParkingSlotType;
import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.repositories.IParkingSlotTypeRepository;
import com.dmavrotas.pts.api.services.interfaces.IService;
import org.springframework.stereotype.Component;

@Component
public class ParkingSlotTypeService implements IService<ParkingSlotType>
{
    protected final IParkingSlotTypeRepository parkingSlotTypeRepository;

    public ParkingSlotTypeService(IParkingSlotTypeRepository parkingSlotTypeRepository)
    {
        this.parkingSlotTypeRepository = parkingSlotTypeRepository;
    }

    @Override
    public ParkingSlotType getEntity(int id)
    {
        return parkingSlotTypeRepository.findById(id).orElse(null);
    }

    @Override
    public ParkingSlotType saveEntity(ParkingSlotType entity)
    {
        if(entity == null)
        {
            return null;
        }

        return parkingSlotTypeRepository.save(entity);
    }

    @Override
    public boolean deleteEntity(ParkingSlotType entity)
    {
        if (entity == null)
        {
            return false;
        }

        parkingSlotTypeRepository.delete(entity);

        return parkingSlotTypeRepository.findById(entity.getId()).orElse(null) == null;
    }

    public Iterable<ParkingSlotType> getAllEntities()
    {
        return parkingSlotTypeRepository.findAll();
    }

    public ParkingSlotType findParkingSlotTypeByName(EParkingSlotType name)
    {
        return parkingSlotTypeRepository.findParkingSlotTypeByName(name);
    }
}
