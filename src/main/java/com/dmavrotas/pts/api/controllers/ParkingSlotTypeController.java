package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.ParkingSlotType;
import com.dmavrotas.pts.api.services.ParkingSlotTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/pts/parkingSlotType")
public class ParkingSlotTypeController
{
    protected final ParkingSlotTypeService parkingService;

    public ParkingSlotTypeController(ParkingSlotTypeService parkingService)
    {
        this.parkingService = parkingService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<ParkingSlotType> get()
    {
        return parkingService.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ParkingSlotType get(@PathVariable(name = "id") int id)
    {
        return parkingService.getEntity(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public ParkingSlotType save(@RequestBody ParkingSlotType parkingSlotType)
    {
        return parkingService.saveEntity(parkingSlotType);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean delete(@PathVariable(name = "id") int id)
    {
        return parkingService.deleteEntity(parkingService.getEntity(id));
    }
}
