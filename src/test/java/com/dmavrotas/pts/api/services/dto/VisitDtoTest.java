package com.dmavrotas.pts.api.services.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisitDtoTest
{
    @Test
    void test()
    {
        var visitDto = new VisitDto(1, "A", "B");

        visitDto.setParkingId(1);
        visitDto.setRegistrationPlate("A");
        visitDto.setParkingSlotType("B");

        assertEquals(1, visitDto.getParkingId());
        assertEquals("A", visitDto.getRegistrationPlate());
        assertEquals("B", visitDto.getParkingSlotTypeName());

        var visitDto2 = new VisitDto(1, "A", "B");

        assertEquals(visitDto, visitDto2);
        assertNotSame(visitDto, visitDto2);
        assertNotEquals(visitDto, null);

        var visitDto3 = new VisitDto(1, "B", "A");

        assertNotEquals(visitDto, visitDto3);
        assertNotSame(visitDto, visitDto2);

        var visitDtoCopy = visitDto;

        assertEquals(visitDto, visitDtoCopy);
        assertSame(visitDto, visitDtoCopy);

        assertEquals(visitDto.toString(), visitDtoCopy.toString());
    }
}
