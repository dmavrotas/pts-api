package com.dmavrotas.pts.api.services.pricingpolicies;

import com.dmavrotas.pts.api.services.pricingpolicies.interfaces.IPricingPolicy;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.Duration;
import java.time.LocalDateTime;

public class PerHourPricingPolicy implements IPricingPolicy
{
    private float pricePerHour;

    public PerHourPricingPolicy(float pricePerHour)
    {
        this.pricePerHour = pricePerHour;
    }

    public float getPricePerHour()
    {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour)
    {
        this.pricePerHour = pricePerHour;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof PerHourPricingPolicy))
        {
            return false;
        }

        PerHourPricingPolicy that = (PerHourPricingPolicy) o;

        return new EqualsBuilder()
                       .append(pricePerHour, that.pricePerHour)
                       .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                       .append(pricePerHour)
                       .toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                       .append("pricePerHour", pricePerHour)
                       .toString();
    }

    @Override
    public float calculateParkingPrice(LocalDateTime entryTime, LocalDateTime exitTime)
    {
        if (entryTime == null || exitTime == null)
        {
            throw new IllegalArgumentException("Times of entry or/and exit cannot be null");
        }

        return Duration.between(entryTime, exitTime).toHours() * pricePerHour;
    }
}
