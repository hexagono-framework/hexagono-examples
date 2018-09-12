package com.github.hexagonoframework.example.webapplication.infrastructure.jaxrs;

public class ErrorResponseEntity {

    private String code;
    private String message;

    public ErrorResponseEntity(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    
}
