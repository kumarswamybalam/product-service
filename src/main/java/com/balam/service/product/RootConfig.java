package com.balam.service.product;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.util.TimeZone;

@Configuration
public class RootConfig {

    @Bean
    ObjectMapper objectMapper() {
       ObjectMapper objectMapper = new ObjectMapper();
       objectMapper
               .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
               .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
               .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

       objectMapper.registerModule(new JavaTimeModule());
       objectMapper.setTimeZone(TimeZone.getDefault());
       return objectMapper;
    }

    @Bean
    Clock clock() {
        return Clock.systemDefaultZone();
    }
}
