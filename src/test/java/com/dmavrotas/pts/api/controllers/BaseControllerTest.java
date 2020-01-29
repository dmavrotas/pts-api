package com.dmavrotas.pts.api.controllers;

import com.dmavrotas.pts.api.models.AbstractEntity;
import com.dmavrotas.pts.api.repositories.RepositoryTestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

abstract class BaseControllerTest extends RepositoryTestHelper
{
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    protected final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeEach
    void childSetup()
    {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    protected AbstractEntity byteArrayToClass(byte[] data)
    {
        AbstractEntity obj = null;

        try
        {
            var in = new ObjectInputStream(new ByteArrayInputStream(data));
            obj = (AbstractEntity) in.readObject();
            in.close();
        }
        catch (IOException | ClassCastException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return obj;
    }
}
