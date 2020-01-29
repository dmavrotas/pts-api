package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.VisitLog;
import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.services.VisitLogService;
import com.dmavrotas.pts.api.services.dto.VisitDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/pts/visitLog")
public class VisitLogController
{
    protected final VisitLogService visitLogService;

    public VisitLogController(VisitLogService visitLogService)
    {
        this.visitLogService = visitLogService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<VisitLog> get()
    {
        return visitLogService.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public VisitLog get(@PathVariable(name = "id") int id)
    {
        return visitLogService.getEntity(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public VisitLog save(@RequestBody VisitLog visitLog)
    {
        return visitLogService.saveEntity(visitLog);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean delete(@PathVariable(name = "id") int id)
    {
        return visitLogService.deleteEntity(visitLogService.getEntity(id));
    }

    @PostMapping(value = "/checkIn")
    @ResponseStatus(value = HttpStatus.OK)
    public VisitLog checkIn(@RequestBody VisitDto visitDto)
    {
        return visitLogService.checkIn(visitDto.getParkingId(), visitDto.getRegistrationPlate(),
                                       EParkingSlotType.valueOf(visitDto.getParkingSlotTypeName()));
    }

    @PostMapping(value = "/checkOut")
    @ResponseStatus(value = HttpStatus.OK)
    public VisitLog checkOut(@RequestBody VisitDto visitDto)
    {
        return visitLogService.checkOut(visitDto.getParkingId(), visitDto.getRegistrationPlate());
    }
}
