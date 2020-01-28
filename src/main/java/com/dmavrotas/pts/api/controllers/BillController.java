package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.Bill;
import com.dmavrotas.pts.api.services.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/pts/bill")
public class BillController
{
    protected final BillService billService;

    public BillController(BillService billService)
    {
        this.billService = billService;
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

    @PostMapping()
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
}
