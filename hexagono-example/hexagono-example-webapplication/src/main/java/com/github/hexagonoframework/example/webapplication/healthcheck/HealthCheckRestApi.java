package com.github.hexagonoframework.example.webapplication.healthcheck;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/health")
public class HealthCheckRestApi {

    private enum ITEM { H2_DATABASE }
    private Map<ITEM, HealthCheck> checkers = new EnumMap<>(ITEM.class);
    
    @Inject
    private H2HealthCheck h2;
    
    @PostConstruct
    public void init() {
        checkers.put(ITEM.H2_DATABASE, h2);
    }
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,Health> health() {
        Map<String,Health> healths = new HashMap<>();
        for (ITEM item : ITEM.values()) {
            healths.put(item.name(), check(item));
        }
        
        return healths;
    }

    @GET
    @Path("/{item}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Health health(@PathParam("item") String value) {
        ITEM item;
        try {
            item = ITEM.valueOf(value);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new NotFoundException(e);
        }
        
        return check(item);
    }
    
    private Health check(ITEM item) {
        return checkers.get(item).check();
    }

}
