package com.dmavrotas.pts.api.utils;

import com.dmavrotas.pts.api.models.interfaces.Identifiable;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

/**
 * Hack class to handle both given Id and auto generated ids while inserting data
 *
 * to be used with
 * @GenericGenerator(name = "assigned-identity", strategy = "com.dmavrotas.pts.api.utils.AssignedIdentityGenerator")
 * @GeneratedValue(generator = "assigned-identity", strategy = GenerationType.IDENTITY)
 */
public class AssignedIdentityGenerator extends IdentityGenerator
{
    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj)
    {
        if (obj instanceof Identifiable)
        {
            var id = ((Identifiable)obj).getId();

            if (id != null)
            {
                return id;
            }
        }
        return super.generate(s, obj);
    }
}

