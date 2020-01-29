package com.dmavrotas.pts.api.config;

import com.dmavrotas.pts.api.PtsApiApplicationTests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PtsApiApplicationTests.class)
@ActiveProfiles({"test"})
public class SwaggerConfigurationTest
{
    @Autowired
    SwaggerConfiguration swaggerConfiguration;

    @Test
    public void test()
    {
        assertNotNull(swaggerConfiguration.api());
    }
}
