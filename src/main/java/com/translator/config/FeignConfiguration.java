package com.translator.config;

import com.translator.error.handler.AzureClientErrorHandler;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    private static final String KEY_HEADER = "Ocp-Apim-Subscription-Key";

    @Value("${azure.access-key}")
    private String accessKey;

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                requestTemplate.header(KEY_HEADER, accessKey);
            }
        };
    }

    @Bean
    public AzureClientErrorHandler errorHandler() {
        return new AzureClientErrorHandler();
    }
}
