package com.nikulin.buildKeeper.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public ValidationException(String message) {
        super(message);
    }
}
