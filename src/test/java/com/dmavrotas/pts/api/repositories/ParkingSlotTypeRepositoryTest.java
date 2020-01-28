package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.models.ParkingSlotType;
import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingSlotTypeRepositoryTest extends RepositoryTestHelper
{
    @Test
    void repositoryTest()
    {
        assertEquals(0, ((ArrayList)parkingSlotTypeRepository.findAll()).size());

        var parkingSlotType = new ParkingSlotType();

        parkingSlotType.setId(1);
        parkingSlotType.setName(EParkingSlotType.STANDARD);
        parkingSlotType.setCreated(LocalDateTime.now());

        var savedParkingSlotType = parkingSlotTypeRepository.save(parkingSlotType);

        assertNotNull(savedParkingSlotType);
        assertEquals(EParkingSlotType.STANDARD, savedParkingSlotType.getName());
    }
}
