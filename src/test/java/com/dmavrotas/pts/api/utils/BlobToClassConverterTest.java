package com.dmavrotas.pts.api.utils;

import com.dmavrotas.pts.api.services.pricingpolicies.FixedPlusPerHourPricingPolicy;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

class BlobToClassConverterTest
{
    @Test
    void testConvertToDatabaseColumn()
    {
        var classUnderTest = new BlobToClassConverter();

        var fixedPlusPerHourPricingPolicy = new FixedPlusPerHourPricingPolicy(5.0f, 2.5f);

        byte[] fixedPlusPerHourByteArray = null;

        try
        {
            var bos = new ByteArrayOutputStream();
            var oos = new ObjectOutputStream(bos);
            oos.writeObject(fixedPlusPerHourPricingPolicy);
            oos.flush();
            fixedPlusPerHourByteArray = bos.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        var result = classUnderTest.convertToDatabaseColumn(fixedPlusPerHourPricingPolicy);

        assertNotNull(result);
        assertEquals(fixedPlusPerHourByteArray.length, result.length);
        assertNotSame(fixedPlusPerHourByteArray, result);
    }

    @Test
    void testConvertToDatabaseColumnFail()
    {
        var classUnderTest = new BlobToClassConverter();

        assertNotNull(classUnderTest.convertToDatabaseColumn(null));
    }

    @Test
    void testConvertToEntityAttribute()
    {
        var classUnderTest = new BlobToClassConverter();

        var fixedPlusPerHourPricingPolicy = new FixedPlusPerHourPricingPolicy(5.0f, 2.5f);

        var byteArray = classUnderTest.convertToDatabaseColumn(fixedPlusPerHourPricingPolicy);

        var result = classUnderTest.convertToEntityAttribute(byteArray);

        assertNotNull(result);
        assertEquals(result, fixedPlusPerHourPricingPolicy);
        assertNotSame(result, fixedPlusPerHourPricingPolicy);
    }

    @Test
    void testConvertToEntityAttributeFail()
    {
        var classUnderTest = new BlobToClassConverter();

        assertThrows(NullPointerException.class, () -> classUnderTest.convertToEntityAttribute(null));

        var testClass = new TestClass(10);
        byte[] testClassByteArray = null;

        try
        {
            var bos = new ByteArrayOutputStream();
            var oos = new ObjectOutputStream(bos);
            oos.writeObject(testClass);
            oos.flush();
            testClassByteArray = bos.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        var properFinalTestClassByteArray = testClassByteArray;

        assertNull(classUnderTest.convertToEntityAttribute(properFinalTestClassByteArray));
    }

    static class TestClass implements Serializable
    {
        private int id;

        public TestClass(int id)
        {
            this.id = id;
        }

        public int getId()
        {
            return id;
        }

    }
}
