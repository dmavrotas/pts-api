package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.Bill;
import com.dmavrotas.pts.api.services.BillService;
import com.dmavrotas.pts.api.services.ParkingService;
import com.dmavrotas.pts.api.services.ParkingSlotService;
import com.dmavrotas.pts.api.services.VisitLogService;
import com.dmavrotas.pts.api.services.dto.BillDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/pts/bill")
public class BillController
{
    protected final BillService billService;
    protected final VisitLogService visitLogService;
    protected final ParkingService parkingService;
    protected final ParkingSlotService parkingSlotService;

    public BillController(BillService billService,
                          VisitLogService visitLogService,
                          ParkingService parkingService,
                          ParkingSlotService parkingSlotService)
    {
        this.billService = billService;
        this.visitLogService = visitLogService;
        this.parkingService = parkingService;
        this.parkingSlotService = parkingSlotService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<Bill> get()
    {
        return billService.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Bill get(@PathVariable(name = "id") int id)
    {
        return billService.getEntity(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Bill save(@RequestBody Bill bill)
    {
        return billService.saveEntity(bill);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean delete(@PathVariable(name = "id") int id)
    {
        return billService.deleteEntity(billService.getEntity(id));
    }

    @PostMapping(value = "/payment")
    public Bill save(@RequestBody BillDto billDto)
    {
        var visitLog = visitLogService.getEntity(billDto.getVisitLogId());
        var parking = parkingService.getEntity(billDto.getParkingId());
        var parkingSlot = parkingSlotService.getEntity(billDto.getParkingSlotId());

        return billService.createPaymentFromVisitLogAndParkingSlot(parking, parkingSlot, visitLog);
    }
}
