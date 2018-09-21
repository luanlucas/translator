package com.translator.error.handler;

import com.translator.exception.ServiceNotFoundExeption;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class AzureClientErrorHandler implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (HttpStatus.NOT_FOUND.value() == response.status()) {
            return new ServiceNotFoundExeption(String.format("It was not possible to connect to Azure API due to %s .", response.reason()));
        }

        return FeignException.errorStatus(methodKey, response);
    }
}
