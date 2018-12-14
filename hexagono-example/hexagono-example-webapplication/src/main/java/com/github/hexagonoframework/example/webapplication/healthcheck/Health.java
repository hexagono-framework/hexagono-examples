package com.github.hexagonoframework.example.webapplication.healthcheck;

import java.util.HashMap;
import java.util.Map;

public class Health {

    enum Status {
        OK,
        ERROR,
        UNAVAILABLE,
        UNKNOWN,
        WARN
    }
    
    public final Status status;
    public final String message;
    public final Map<String, String> details;
    
    public Health(Status status, String message, Map<String, String> details) {
        super();
        this.status = status;
        this.message = message;
        this.details = details;
    }
    
    public static Health ok() {
        return new Health(Status.OK, null, null);
    }
    
    public static Health error(Throwable throwable) {
        Map<String, String> details = new HashMap<>();
        details.put("exceptionClass", throwable.getClass().getName());
        details.put("exceptionStackTrace", throwable.getStackTrace().toString());
        return new Health(Status.ERROR, throwable.getMessage(), details);
    }

    public static Health error(String message) {
        return new Health(Status.ERROR, message, null);
    }
    
}
