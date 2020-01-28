package com.dmavrotas.pts.api.utils;

import com.dmavrotas.pts.api.services.pricingpolicies.interfaces.IPricingPolicy;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.*;

@Converter
public class BlobToClassConverter implements AttributeConverter<IPricingPolicy, byte[]>
{
    @Override
    public byte[] convertToDatabaseColumn(IPricingPolicy attribute)
    {
        byte[] result = null;

        try
        {
            var bos = new ByteArrayOutputStream();
            var oos = new ObjectOutputStream(bos);
            oos.writeObject(attribute);
            oos.flush();
            result = bos.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public IPricingPolicy convertToEntityAttribute(byte[] dbData)
    {
        IPricingPolicy pricingPolicy = null;

        try
        {
            var in = new ObjectInputStream(new ByteArrayInputStream(dbData));
            pricingPolicy = (IPricingPolicy) in.readObject();
            in.close();
        }
        catch (IOException | ClassCastException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return pricingPolicy;
    }
}
