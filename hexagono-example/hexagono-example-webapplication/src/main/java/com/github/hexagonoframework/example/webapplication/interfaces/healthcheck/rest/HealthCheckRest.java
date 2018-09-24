package com.github.hexagonoframework.example.webapplication.interfaces.healthcheck.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/health")
public class HealthCheckRest {

    @GET
    @Path("/status")
    @Produces(MediaType.TEXT_PLAIN)
    public Response status() {
        return Response.ok().entity("OK").build();
    }


}
