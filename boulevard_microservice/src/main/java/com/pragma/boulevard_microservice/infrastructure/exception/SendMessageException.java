package com.pragma.boulevard_microservice.infrastructure.exception;

public class SendMessageException extends RuntimeException {
    public SendMessageException() {
        super();
    }

    public SendMessageException(String message) {
        super(message);
    }
}
