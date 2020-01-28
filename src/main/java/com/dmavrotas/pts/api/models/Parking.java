package com.dmavrotas.pts.api.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking")
public class Parking extends AbstractEntity
{
    @JoinColumn(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "pricing_policy_id")
    private PricingPolicy pricingPolicy;

    @Column(name = "created")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime created;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public LocalDateTime getCreated()
    {
        return created;
    }

    public void setCreated(LocalDateTime created)
    {
        this.created = created;
    }

    public PricingPolicy getPricingPolicy()
    {
        return pricingPolicy;
    }

    public void setPricingPolicy(PricingPolicy pricingPolicy)
    {
        this.pricingPolicy = pricingPolicy;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (!(o instanceof Parking))
        {
            return false;
        }

        Parking parking = (Parking) o;

        return new EqualsBuilder()
                       .append(id, parking.id)
                       .append(name, parking.name)
                       .append(pricingPolicy, parking.pricingPolicy)
                       .append(created, parking.created)
                       .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                       .append(id)
                       .append(name)
                       .append(pricingPolicy)
                       .append(created)
                       .toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                       .append("id", id)
                       .append("name", name)
                       .append("pricingPolicy", pricingPolicy)
                       .append("created", created)
                       .toString();
    }
}
