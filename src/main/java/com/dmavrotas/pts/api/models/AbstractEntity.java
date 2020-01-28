package com.dmavrotas.pts.api.models;

import com.dmavrotas.pts.api.models.interfaces.Identifiable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity implements Identifiable, Serializable
{
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "assigned-identity",
                      strategy = "com.dmavrotas.pts.api.utils.AssignedIdentityGenerator")
    @GeneratedValue(generator = "assigned-identity", strategy = GenerationType.IDENTITY)
    protected Integer id;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
}
