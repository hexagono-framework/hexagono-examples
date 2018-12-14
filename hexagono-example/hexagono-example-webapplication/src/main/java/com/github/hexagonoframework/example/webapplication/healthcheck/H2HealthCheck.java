package com.github.hexagonoframework.example.webapplication.healthcheck;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.sql.DataSource;

@Dependent
public class H2HealthCheck implements HealthCheck {

    private static final int timeoutInSeconds = 10;
    
    @Resource(mappedName = "java:jboss/datasources/ExampleDS")
    private DataSource dataSource;
    
    @Override
    public Health check() {
        try (Connection connection = dataSource.getConnection()) {
            if (!connection.isValid(timeoutInSeconds)) {
                return Health.error("Could not establish a valid connection to H2 database");
            }
        } catch (SQLException e) {
            return Health.error(e);
        }
        
        return Health.ok();
    }

}
