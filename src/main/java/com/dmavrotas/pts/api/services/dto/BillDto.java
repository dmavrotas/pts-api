package com.dmavrotas.pts.api.services.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BillDto
{
    private int parkingId;
    private int parkingSlotId;
    private int visitLogId;

    public BillDto(int parkingId, int parkingSlotId, int visitLogId)
    {
        this.parkingId = parkingId;
        this.parkingSlotId = parkingSlotId;
        this.visitLogId = visitLogId;
    }

    public int getParkingId()
    {
        return parkingId;
    }

    public void setParkingId(int parkingId)
    {
        this.parkingId = parkingId;
    }

    public int getParkingSlotId()
    {
        return parkingSlotId;
    }

    public void setParkingSlotId(int parkingSlotId)
    {
        this.parkingSlotId = parkingSlotId;
    }

    public int getVisitLogId()
    {
        return visitLogId;
    }

    public void setVisitLogId(int visitLogId)
    {
        this.visitLogId = visitLogId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof BillDto))
        {
            return false;
        }

        BillDto billDto = (BillDto) o;

        return new EqualsBuilder()
                       .append(parkingId, billDto.parkingId)
                       .append(parkingSlotId, billDto.parkingSlotId)
                       .append(visitLogId, billDto.visitLogId)
                       .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                       .append(parkingId)
                       .append(parkingSlotId)
                       .append(visitLogId)
                       .toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                       .append("parkingId", parkingId)
                       .append("parkingSlotId", parkingSlotId)
                       .append("visitLogId", visitLogId)
                       .toString();
    }
}
