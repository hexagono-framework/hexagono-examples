package com.github.hexagonoframework.example.webapplication.infrastructure.logging;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.github.hexagonoframework.example.webapplication.infrastructure.jpa.JpaLog;
import com.github.hexagonoframework.example.webapplication.infrastructure.jpa.JpaLogRepository;

@Dependent
@Transactional
public class DatabaseLoggingService implements LoggingService {

    private static final Logger LOGGER = Logger.getLogger(DatabaseLoggingService.class.getSimpleName());

    @Inject
    private JpaLogRepository repository;
    
    @Override
    public void debug() {
        // TODO Auto-generated method stub
    }

    @Override
    public void info(String message) {
        JpaLog entity = new JpaLog();
        entity.setId(UUID.randomUUID().toString());
        entity.setWhen(LocalDateTime.now());
        entity.setMessage(message);
        repository.saveAndFlush(entity);
    }

    @Override
    public void warn() {
        // TODO Auto-generated method stub

    }

    @Override
    public void error(String message) {
        LOGGER.severe(message);
    }

}
