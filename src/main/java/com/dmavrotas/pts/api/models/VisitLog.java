package com.dmavrotas.pts.api.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visit_log")
public class VisitLog extends AbstractEntity
{
    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "entry_time")
    private LocalDateTime entryTime;

    @Column(name = "exit_time")
    private LocalDateTime exitTime;

    public Parking getParking()
    {
        return parking;
    }

    public void setParking(Parking parking)
    {
        this.parking = parking;
    }

    public Car getCar()
    {
        return car;
    }

    public void setCar(Car car)
    {
        this.car = car;
    }

    public LocalDateTime getEntryTime()
    {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime)
    {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime()
    {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime)
    {
        this.exitTime = exitTime;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof VisitLog))
        {
            return false;
        }

        VisitLog visitLog = (VisitLog) o;

        return new EqualsBuilder()
                       .append(id, visitLog.id)
                       .append(parking, visitLog.parking)
                       .append(car, visitLog.car)
                       .append(entryTime, visitLog.entryTime)
                       .append(exitTime, visitLog.exitTime)
                       .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                       .append(id)
                       .append(parking)
                       .append(car)
                       .append(entryTime)
                       .append(exitTime)
                       .toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                       .append("id", id)
                       .append("parking", parking)
                       .append("car", car)
                       .append("entryTime", entryTime)
                       .append("exitTime", exitTime)
                       .toString();
    }
}
