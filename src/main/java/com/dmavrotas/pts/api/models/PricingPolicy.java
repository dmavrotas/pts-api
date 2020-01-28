package com.dmavrotas.pts.api.models;

import com.dmavrotas.pts.api.models.enums.EPricingPolicy;
import com.dmavrotas.pts.api.services.pricingpolicies.interfaces.IPricingPolicy;
import com.dmavrotas.pts.api.utils.BlobToClassConverter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pricing_policy")
public class PricingPolicy extends AbstractEntity
{
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private EPricingPolicy name;

    @Column(name = "formula")
    @Convert(converter = BlobToClassConverter.class)
    private IPricingPolicy formula;

    @Column(name = "created")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime created;

    public EPricingPolicy getName()
    {
        return name;
    }

    public void setName(EPricingPolicy name)
    {
        this.name = name;
    }

    public IPricingPolicy getFormula()
    {
        return formula;
    }

    public void setFormula(IPricingPolicy formula)
    {
        this.formula = formula;
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

        if (!(o instanceof PricingPolicy))
        {
            return false;
        }

        PricingPolicy that = (PricingPolicy) o;

        return new EqualsBuilder()
                       .append(id, that.id)
                       .append(name, that.name)
                       .append(created, that.created)
                       .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                       .append(id)
                       .append(name)
                       .append(created)
                       .toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                       .append("id", id)
                       .append("name", name)
                       .append("created", created)
                       .toString();
    }
}
