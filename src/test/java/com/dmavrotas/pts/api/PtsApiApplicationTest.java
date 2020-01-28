package com.dmavrotas.pts.api;


import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.mockito.Mockito.*;

class PtsApiApplicationTest
{
    @Test
    void test()
    {
        var app = new PtsApiApplication();


        var source = mock(SpringApplicationBuilder.class);

        app.configure(source);

        verify(source, times(1)).sources(PtsApiApplication.class);
    }
}
