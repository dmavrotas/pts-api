package com.dmavrotas.pts.api.services;

import com.dmavrotas.pts.api.models.VisitLog;
import com.dmavrotas.pts.api.repositories.IVisitLogRepository;
import com.dmavrotas.pts.api.services.interfaces.IService;
import org.springframework.stereotype.Component;

@Component
public class VisitLogService implements IService<VisitLog>
{
    protected final IVisitLogRepository visitLogRepository;
    protected final ParkingService parkingService;
    protected final CarService carService;

    public VisitLogService(IVisitLogRepository visitLogRepository, ParkingService parkingService, CarService carService)
    {
        this.visitLogRepository = visitLogRepository;
        this.parkingService = parkingService;
        this.carService = carService;
    }

    @Override
    public VisitLog getEntity(int id)
    {
        return visitLogRepository.findById(id).orElse(null);
    }

    @Override
    public VisitLog saveEntity(VisitLog entity)
    {
        if (entity == null)
        {
            return null;
        }

        if(entity.getCar() != null)
        {
            carService.saveEntity(entity.getCar());
        }

        if(entity.getParking() != null)
        {
            parkingService.saveEntity(entity.getParking());
        }

        return visitLogRepository.save(entity);
    }

    @Override
    public boolean deleteEntity(VisitLog entity)
    {
        if (entity == null)
        {
            return false;
        }

        visitLogRepository.delete(entity);

        return visitLogRepository.findById(entity.getId()).orElse(null) == null;
    }
}
