package com.github.hexagonoframework.example.webapplication.interfaces.healthcheck.rest;

import static io.restassured.RestAssured.get;

import org.hamcrest.Matchers;
import org.junit.Test;

public class HealthCheckRestIT {

    @Test
    public void testStatus() {
        get("/hexagono-example/api/health/status").then().assertThat().statusCode(200)
                .body(Matchers.containsString("OK"));

    }

}
