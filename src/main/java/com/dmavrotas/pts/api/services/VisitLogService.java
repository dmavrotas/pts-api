package com.dmavrotas.pts.api.services;

import com.dmavrotas.pts.api.exceptions.*;
import com.dmavrotas.pts.api.models.Car;
import com.dmavrotas.pts.api.models.VisitLog;
import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.repositories.IVisitLogRepository;
import com.dmavrotas.pts.api.services.interfaces.IService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VisitLogService implements IService<VisitLog>
{
    protected final IVisitLogRepository visitLogRepository;
    protected final ParkingService parkingService;
    protected final CarService carService;
    protected final ParkingSlotService parkingSlotService;
    protected final ParkingSlotTypeService parkingSlotTypeService;

    public VisitLogService(IVisitLogRepository visitLogRepository,
                           ParkingService parkingService,
                           CarService carService,
                           ParkingSlotService parkingSlotService,
                           ParkingSlotTypeService parkingSlotTypeService)
    {
        this.visitLogRepository = visitLogRepository;
        this.parkingService = parkingService;
        this.carService = carService;
        this.parkingSlotService = parkingSlotService;
        this.parkingSlotTypeService = parkingSlotTypeService;
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

        if (entity.getCar() != null)
        {
            carService.saveEntity(entity.getCar());
        }

        if (entity.getParking() != null)
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

    public Iterable<VisitLog> getAllEntities()
    {
        return visitLogRepository.findAll();
    }

    /**
     * @param parkingId
     * @param registrationPlate
     * @param parkingSlotType
     */
    public VisitLog checkIn(int parkingId, String registrationPlate, EParkingSlotType parkingSlotType)
    {
        var parking = parkingService.getEntity(parkingId);

        if (parking == null)
        {
            throw new ParkingNotFoundException("Parking with id " + parkingId + " could not be found");
        }

        var car = carService.getCarByRegistrationPlate(registrationPlate);

        if (car == null)
        {
            car = new Car();

            car.setRegistrationPlate(registrationPlate);
            car.setCreated(LocalDateTime.now());

            carService.saveEntity(car);
        }

        var parkingSlotTypeFromDb = parkingSlotTypeService.findParkingSlotTypeByName(parkingSlotType);

        if (parkingSlotTypeFromDb == null)
        {
            throw new ParkingSlotTypeNotFound("ParkingSlotType with name" + parkingSlotType.name() + " was not found");
        }

        var parkingSlot = parkingSlotService.findFreeSlotsForType(parkingId, parkingSlotTypeFromDb.getId());

        if (parkingSlot == null)
        {
            throw new ParkingSlotNotAvailableException(
                    "No available position for " + parkingSlotType.name() + " type cars");
        }

        parkingSlot.setCar(car);
        parkingSlot.setFree(false);

        parkingSlotService.saveEntity(parkingSlot);

        var visitLog = new VisitLog();

        visitLog.setParking(parking);
        visitLog.setCar(car);
        visitLog.setEntryTime(LocalDateTime.now());

        return visitLogRepository.save(visitLog);
    }

    /**
     * @param parkingId
     * @param registrationPlate
     */
    public VisitLog checkOut(int parkingId, String registrationPlate)
    {
        var parking = parkingService.getEntity(parkingId);

        if (parking == null)
        {
            throw new ParkingNotFoundException("Parking with id " + parkingId + " could not be found");
        }

        var car = carService.getCarByRegistrationPlate(registrationPlate);

        if (car == null)
        {
            throw new InvalidRegistrationPlateException(
                    "No car with registration plate " + registrationPlate + " was found");
        }

        var parkingSlot = parkingSlotService.findParkingSlotByCarId(car.getId());

        if (parkingSlot == null)
        {
            throw new CarNotParkedException("The car was not parked");
        }

        parkingSlot.setFree(true);
        parkingSlot.setCar(null);

        var visitLog = visitLogRepository.findLatestVisitByCarId(parkingId, car.getId());

        if (visitLog == null)
        {
            throw new VisitLogNotFoundException("VisitLogNotFound");
        }

        visitLog.setExitTime(LocalDateTime.now());

        return visitLogRepository.save(visitLog);
    }
}
