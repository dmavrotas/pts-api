package com.dmavrotas.pts.api.models;

import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_slot_type")
public class ParkingSlotType extends AbstractEntity
{
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private EParkingSlotType name;

    @Column(name = "created")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime created;

    public EParkingSlotType getName()
    {
        return name;
    }

    public void setName(EParkingSlotType name)
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

        ParkingSlotType that = (ParkingSlotType) o;

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
