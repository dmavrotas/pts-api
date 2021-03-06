package com.dmavrotas.pts.api.services;

import com.dmavrotas.pts.api.models.Bill;
import com.dmavrotas.pts.api.models.Parking;
import com.dmavrotas.pts.api.models.ParkingSlot;
import com.dmavrotas.pts.api.models.VisitLog;
import com.dmavrotas.pts.api.repositories.IBillRepository;
import com.dmavrotas.pts.api.services.interfaces.IService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

    /**
     * Make the client pay for his/her stay in the parking
     *
     * @param parking     The parking
     * @param parkingSlot The parkingSlot in which the car was parked
     * @param visitLog    The visitLog item which it's the current visit of the car and contains the entry and the exit time
     * @return The bill with the amount depending on the pricing policy
     */
    public Bill createPaymentFromVisitLogAndParkingSlot(Parking parking, ParkingSlot parkingSlot, VisitLog visitLog)
    {
        var bill = new Bill();

        bill.setAmount(parking.getPricingPolicy().getFormula()
                              .calculateParkingPrice(visitLog.getEntryTime(), visitLog.getExitTime()));
        bill.setParkingSlot(parkingSlot);
        bill.setVisitLog(visitLog);
        bill.setCreated(LocalDateTime.now());

        return billRepository.save(bill);
    }
}
