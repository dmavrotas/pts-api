package com.dmavrotas.pts.api.repositories;

import com.dmavrotas.pts.api.PtsApiApplicationTests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PtsApiApplicationTests.class)
@ActiveProfiles({"test"})
public abstract class RepositoryTestHelper
{
    @Autowired
    protected IBillRepository billRepository;
    @Autowired
    protected ICarRepository carRepository;
    @Autowired
    protected IVisitLogRepository visitLogRepository;
    @Autowired
    protected IParkingRepository parkingRepository;
    @Autowired
    protected IParkingSlotRepository parkingSlotRepository;
    @Autowired
    protected IParkingSlotTypeRepository parkingSlotTypeRepository;
    @Autowired
    protected IPricingPolicyRepository pricingPolicyRepository;

    @BeforeEach
    void setUp()
    {
        billRepository.deleteAll();
        visitLogRepository.deleteAll();
        parkingRepository.deleteAll();
        parkingSlotRepository.deleteAll();
        parkingSlotTypeRepository.deleteAll();
        pricingPolicyRepository.deleteAll();
        carRepository.deleteAll();
    }

    @AfterEach
    void tearDown()
    {
        billRepository.deleteAll();
        visitLogRepository.deleteAll();
        parkingRepository.deleteAll();
        parkingSlotRepository.deleteAll();
        parkingSlotTypeRepository.deleteAll();
        pricingPolicyRepository.deleteAll();
        carRepository.deleteAll();
    }
}
