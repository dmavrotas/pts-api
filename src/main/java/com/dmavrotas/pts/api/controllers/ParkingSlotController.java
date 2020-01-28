package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.ParkingSlot;
import com.dmavrotas.pts.api.services.ParkingSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/pts/parkingSlot")
public class ParkingSlotController
{
    protected final ParkingSlotService parkingService;

    public ParkingSlotController(ParkingSlotService parkingService)
    {
        this.parkingService = parkingService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<ParkingSlot> get()
    {
        return parkingService.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ParkingSlot get(@PathVariable(name = "id") int id)
    {
        return parkingService.getEntity(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public ParkingSlot save(@RequestBody ParkingSlot parkingSlot)
    {
        return parkingService.saveEntity(parkingSlot);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean delete(@PathVariable(name = "id") int id)
    {
        return parkingService.deleteEntity(parkingService.getEntity(id));
    }
}
