package com.dmavrotas.pts.api.services;

import com.dmavrotas.pts.api.models.ParkingSlotType;
import com.dmavrotas.pts.api.models.enums.EParkingSlotType;
import com.dmavrotas.pts.api.repositories.RepositoryTestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSlotTypeServiceTest extends RepositoryTestHelper
{
    @Autowired
    private ParkingSlotTypeService parkingSlotTypeService;

    @Test
    void testService()
    {
        var parkingSlotType = new ParkingSlotType();

        parkingSlotType.setName(EParkingSlotType.STANDARD);
        parkingSlotType.setCreated(LocalDateTime.now());

        var savedParkingSlotType = parkingSlotTypeService.saveEntity(parkingSlotType);

        assertNotNull(savedParkingSlotType);
        assertEquals(1, ((ArrayList) parkingSlotTypeRepository.findAll()).size());

        savedParkingSlotType.setName(EParkingSlotType.HIGH_ELECTRICAL_POWER);

        savedParkingSlotType = parkingSlotTypeService.saveEntity(savedParkingSlotType);

        assertNotNull(savedParkingSlotType);
        assertEquals(1, ((ArrayList) parkingSlotTypeService.getAllEntities()).size());
        assertEquals(EParkingSlotType.HIGH_ELECTRICAL_POWER, savedParkingSlotType.getName());

        parkingSlotType = new ParkingSlotType();

        parkingSlotType.setName(EParkingSlotType.LOW_ELECTRICAL_POWER);
        parkingSlotType.setCreated(LocalDateTime.now());

        savedParkingSlotType = parkingSlotTypeService.saveEntity(parkingSlotType);

        assertNotNull(savedParkingSlotType);
        assertEquals(2, ((ArrayList) parkingSlotTypeService.getAllEntities()).size());
        assertEquals(EParkingSlotType.LOW_ELECTRICAL_POWER, savedParkingSlotType.getName());

        assertNull(parkingSlotTypeService.getEntity(-2));

        assertNotNull(parkingSlotTypeService.getEntity(savedParkingSlotType.getId()));

        assertTrue(parkingSlotTypeService.deleteEntity(savedParkingSlotType));
        assertEquals(1, ((ArrayList) parkingSlotTypeService.getAllEntities()).size());

        assertFalse(parkingSlotTypeService.deleteEntity(null));
        assertNull(parkingSlotTypeService.saveEntity(null));

        assertNull(parkingSlotTypeService.findParkingSlotTypeByName(EParkingSlotType.LOW_ELECTRICAL_POWER));

        assertNotNull(parkingSlotTypeService.findParkingSlotTypeByName(EParkingSlotType.HIGH_ELECTRICAL_POWER));
    }
}
