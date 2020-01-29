package com.dmavrotas.pts.api.services.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class VisitDto
{
    private int parkingId;
    private String registrationPlate;
    private String parkingSlotTypeName;

    public VisitDto(int parkingId, String registrationPlate, String parkingSlotTypeName)
    {
        this.parkingId = parkingId;
        this.registrationPlate = registrationPlate;
        this.parkingSlotTypeName = parkingSlotTypeName;
    }

    public int getParkingId()
    {
        return parkingId;
    }

    public void setParkingId(int parkingId)
    {
        this.parkingId = parkingId;
    }

    public String getRegistrationPlate()
    {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate)
    {
        this.registrationPlate = registrationPlate;
    }

    public String getParkingSlotTypeName()
    {
        return parkingSlotTypeName;
    }

    public void setParkingSlotType(String parkingSlotTypeName)
    {
        this.parkingSlotTypeName = parkingSlotTypeName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof VisitDto))
        {
            return false;
        }

        VisitDto that = (VisitDto) o;

        return new EqualsBuilder()
                       .append(parkingId, that.parkingId)
                       .append(registrationPlate, that.registrationPlate)
                       .append(parkingSlotTypeName, that.parkingSlotTypeName)
                       .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                       .append(parkingId)
                       .append(registrationPlate)
                       .append(parkingSlotTypeName)
                       .toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                       .append("parkingId", parkingId)
                       .append("registrationPlate", registrationPlate)
                       .append("parkingSlotType", parkingSlotTypeName)
                       .toString();
    }
}
