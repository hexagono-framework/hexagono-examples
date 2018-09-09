package com.github.hexagonoframework.example.application;

public abstract class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public abstract String errorCode();

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ApplicationException(String message) {
        super(message);
    }
    
}
