package com.dmavrotas.pts.api.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_slot")
public class ParkingSlot extends AbstractEntity
{
    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;

    @ManyToOne
    @JoinColumn(name = "parking_slot_type_id")
    private ParkingSlotType parkingSlotType;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "is_free")
    private boolean isFree;

    @Column(name = "created")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime created;

    public Parking getParking()
    {
        return parking;
    }

    public void setParking(Parking parking)
    {
        this.parking = parking;
    }

    public ParkingSlotType getParkingSlotType()
    {
        return parkingSlotType;
    }

    public void setParkingSlotType(ParkingSlotType parkingSlotType)
    {
        this.parkingSlotType = parkingSlotType;
    }

    public Car getCar()
    {
        return car;
    }

    public void setCar(Car car)
    {
        this.car = car;
    }

    public boolean isFree()
    {
        return isFree;
    }

    public void setFree(boolean free)
    {
        isFree = free;
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

        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        ParkingSlot that = (ParkingSlot) o;

        return new EqualsBuilder()
                       .append(id, that.id)
                       .append(parking, that.parking)
                       .append(car, that.car)
                       .append(isFree, that.isFree)
                       .append(parkingSlotType, that.parkingSlotType)
                       .append(created, that.created)
                       .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                       .append(id)
                       .append(parking)
                       .append(parkingSlotType)
                       .append(car)
                       .append(isFree)
                       .append(created)
                       .toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                       .append("id", id)
                       .append("parkingSlotType", parkingSlotType)
                       .append("car", car)
                       .append("isFree", isFree)
                       .append("created", created)
                       .toString();
    }
}
