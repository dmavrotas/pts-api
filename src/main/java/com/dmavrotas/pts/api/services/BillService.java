package com.dmavrotas.pts.api.services;

import com.dmavrotas.pts.api.models.Bill;
import com.dmavrotas.pts.api.repositories.IBillRepository;
import com.dmavrotas.pts.api.services.interfaces.IService;
import org.springframework.stereotype.Component;

@Component
public class BillService implements IService<Bill>
{
    protected final IBillRepository billRepository;
    protected final ParkingSlotService parkingSlotService;
    protected final VisitLogService visitLogService;

    public BillService(IBillRepository billRepository,
                       ParkingSlotService parkingSlotService,
                       VisitLogService visitLogService)
    {
        this.billRepository = billRepository;
        this.parkingSlotService = parkingSlotService;
        this.visitLogService = visitLogService;
    }

    @Override
    public Bill getEntity(int id)
    {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public Bill saveEntity(Bill entity)
    {
        if (entity == null)
        {
            return null;
        }

        if (entity.getParkingSlot() != null)
        {
            parkingSlotService.saveEntity(entity.getParkingSlot());
        }

        if (entity.getVisitLog() != null)
        {

            visitLogService.saveEntity(entity.getVisitLog());
        }

        return billRepository.save(entity);
    }

    @Override
    public boolean deleteEntity(Bill entity)
    {
        if (entity == null)
        {
            return false;
        }

        billRepository.delete(entity);

        return billRepository.findById(entity.getId()).orElse(null) == null;
    }

    public Iterable<Bill> getAllEntities()
    {
        return billRepository.findAll();
    }
}
