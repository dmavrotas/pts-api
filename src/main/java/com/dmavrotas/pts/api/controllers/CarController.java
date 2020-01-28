package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.Car;
import com.dmavrotas.pts.api.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/pts/car")
public class CarController
{
    protected final CarService carService;

    public CarController(CarService carService)
    {
        this.carService = carService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<Car> get()
    {
        return carService.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Car get(@PathVariable(name = "id") int id)
    {
        return carService.getEntity(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public Car save(@RequestBody Car car)
    {
        return carService.saveEntity(car);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean delete(@PathVariable(name = "id") int id)
    {
        return carService.deleteEntity(carService.getEntity(id));
    }
}
