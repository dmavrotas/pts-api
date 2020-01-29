package com.dmavrotas.pts.api.services.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillDtoTest
{
    @Test
    void test()
    {
        var billDto = new BillDto(1, 1, 1);

        billDto.setVisitLogId(1);
        billDto.setParkingId(1);
        billDto.setParkingSlotId(1);

        assertEquals(1, billDto.getVisitLogId());
        assertEquals(1, billDto.getParkingId());
        assertEquals(1, billDto.getParkingSlotId());

        var billDto2 = new BillDto(1, 1, 1);

        assertEquals(billDto, billDto2);
        assertNotSame(billDto, billDto2);
        assertNotEquals(billDto, null);

        var billDto3 = new BillDto(1, 2, 2);

        assertNotEquals(billDto, billDto3);
        assertNotSame(billDto, billDto3);

        var billDtoCopy = billDto;

        assertEquals(billDto, billDtoCopy);
        assertSame(billDto, billDtoCopy);

        assertEquals(billDto.toString(), billDtoCopy.toString());
    }
}
