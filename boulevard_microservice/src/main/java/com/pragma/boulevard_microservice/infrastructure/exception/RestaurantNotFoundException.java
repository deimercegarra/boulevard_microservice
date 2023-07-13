package com.pragma.boulevard_microservice.infrastructure.exception;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException() {
        super();
    }

    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
