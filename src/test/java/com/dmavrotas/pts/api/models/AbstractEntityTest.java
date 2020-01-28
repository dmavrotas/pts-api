package com.dmavrotas.pts.api.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class AbstractEntityTest
{
    static class AbstractEntityTester extends AbstractEntity
    {
        private double value;

        public double getValue()
        {
            return value;
        }

        public void setValue(double value)
        {
            this.value = value;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }

            if (!(o instanceof AbstractEntityTester))
            {
                return false;
            }

            AbstractEntityTester that = (AbstractEntityTester) o;

            return new EqualsBuilder()
                           .append(id, that.id)
                           .append(value, that.value)
                           .isEquals();
        }

        @Override
        public int hashCode()
        {
            return new HashCodeBuilder(17, 37)
                           .append(id)
                           .append(value)
                           .toHashCode();
        }
    }

    @Test
    void testNormalBehavior()
    {
        var classUnderTest = new AbstractEntityTester();

        classUnderTest.setId(10);
        classUnderTest.setValue(10.0);

        assertEquals(10, classUnderTest.getId());
        assertEquals(10.0, classUnderTest.getValue());

        var classUnderTestCopy = classUnderTest;

        assertSame(classUnderTest, classUnderTestCopy);
        assertEquals(classUnderTest, classUnderTestCopy);
        assertEquals(classUnderTest.toString(), classUnderTestCopy.toString());
    }
}
