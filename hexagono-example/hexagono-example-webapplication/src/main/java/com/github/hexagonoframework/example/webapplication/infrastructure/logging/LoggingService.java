package com.github.hexagonoframework.example.webapplication.infrastructure.logging;

public interface LoggingService {

    void debug();
    void info(String message);
    void warn();
    void error(String message);
    
}
