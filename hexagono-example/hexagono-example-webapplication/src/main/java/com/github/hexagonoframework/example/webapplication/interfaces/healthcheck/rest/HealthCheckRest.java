package com.github.hexagonoframework.example.webapplication.interfaces.healthcheck.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/health")
public class HealthCheckRest {

    @GET
    @Path("/status")
    public Response status() {
        return Response.ok().entity("OK").build();
    }


}
