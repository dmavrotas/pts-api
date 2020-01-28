package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.Parking;
import com.dmavrotas.pts.api.services.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/pts/parking")
public class ParkingController
{
    protected final ParkingService parkingService;

    public ParkingController(ParkingService parkingService)
    {
        this.parkingService = parkingService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<Parking> get()
    {
        return parkingService.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Parking get(@PathVariable(name = "id") int id)
    {
        return parkingService.getEntity(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public Parking save(@RequestBody Parking parking)
    {
        return parkingService.saveEntity(parking);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean delete(@PathVariable(name = "id") int id)
    {
        return parkingService.deleteEntity(parkingService.getEntity(id));
    }
}
