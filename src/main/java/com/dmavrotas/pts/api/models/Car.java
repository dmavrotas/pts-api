package com.dmavrotas.pts.api.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "car")
public class Car extends AbstractEntity
{
    @Column(name = "registration_plate", unique = true)
    private String registrationPlate;

    @Column(name = "created")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime created;

    public String getRegistrationPlate()
    {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate)
    {
        this.registrationPlate = registrationPlate;
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

        Car car = (Car) o;

        return new EqualsBuilder()
                       .append(id, car.id)
                       .append(registrationPlate, car.registrationPlate)
                       .append(created, car.created)
                       .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                       .append(id)
                       .append(registrationPlate)
                       .append(created)
                       .toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
                       .append("id", id)
                       .append("registrationPlate", registrationPlate)
                       .append("created", created)
                       .toString();
    }
}
