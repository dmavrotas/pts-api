package com.dmavrotas.pts.api.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bill")
public class Bill extends AbstractEntity
{
    @ManyToOne
    @JoinColumn(name = "parking_slot_id")
    private ParkingSlot parkingSlot;

    @ManyToOne
    @JoinColumn(name = "visit_log_id")
    private VisitLog visitLog;

    @Column(name = "amount")
    private double amount;

    @Column(name = "created")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime created;

    public ParkingSlot getParkingSlot()
    {
        return parkingSlot;
    }

    public void setParkingSlot(ParkingSlot parkingSlot)
    {
        this.parkingSlot = parkingSlot;
    }

    public VisitLog getVisitLog()
    {
        return visitLog;
    }

    public void setVisitLog(VisitLog visitLog)
    {
        this.visitLog = visitLog;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public LocalDateTime getCreated()
    {
        return created;
    }

    public void setCreated(LocalDateTime created)
    {
        this.created = created;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof Bill))
        {
            return false;
        }

        Bill bill = (Bill) o;

        return new EqualsBuilder()
                       .append(id, bill.id)
                       .append(parkingSlot, bill.parkingSlot)
                       .append(visitLog, bill.visitLog)
                       .append(amount, bill.amount)
                       .append(created, bill.created)
                       .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                       .append(id)
                       .append(parkingSlot)
                       .append(visitLog)
                       .append(amount)
                       .append(created)
                       .toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                       .append("id", id)
                       .append("parkingSlot", parkingSlot)
                       .append("visitLog", visitLog)
                       .append("amount", amount)
                       .append("created", created)
                       .toString();
    }
}
