package com.translator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.translator.client.AzureClient;
import com.translator.service.TranslateService;
import com.translator.service.TranslateServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class TranslateServiceConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder
                .json()
                .build();
    }

    @Bean
    public AzureClient azureClient() {
        return Mockito.mock(AzureClient.class);
    }

    @Bean
    public TranslateService translateService() {
        return new TranslateServiceImpl(this.objectMapper(), this.azureClient());
    }
}
