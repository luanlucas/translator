package com.translator.exception;

public class ServiceNotFoundExeption extends RuntimeException {

    public ServiceNotFoundExeption(final String message) {
        super(message);
    }
}
