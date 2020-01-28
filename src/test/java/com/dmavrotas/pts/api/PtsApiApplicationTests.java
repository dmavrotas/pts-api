package com.dmavrotas.pts.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableCaching
@Profile({"test"})
public class PtsApiApplicationTests
{
    public static void main(String[] args)
    {
        SpringApplication.run(PtsApiApplicationTest.class, args);
    }
}
