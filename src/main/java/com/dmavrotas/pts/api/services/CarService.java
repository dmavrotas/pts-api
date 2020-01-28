package com.dmavrotas.pts.api.services;

import com.dmavrotas.pts.api.models.Car;
import com.dmavrotas.pts.api.repositories.ICarRepository;
import com.dmavrotas.pts.api.services.interfaces.IService;
import org.springframework.stereotype.Component;

@Component
public class CarService implements IService<Car>
{
    protected final ICarRepository carRepository;

    public CarService(ICarRepository carRepository)
    {
        this.carRepository = carRepository;
    }

    @Override
    public Car getEntity(int id)
    {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public Car saveEntity(Car entity)
    {
        if (entity == null)
        {
            return null;
        }

        return carRepository.save(entity);
    }

    @Override
    public boolean deleteEntity(Car entity)
    {
        if (entity == null)
        {
            return false;
        }

        carRepository.delete(entity);

        return carRepository.findById(entity.getId()).orElse(null) == null;
    }
}
