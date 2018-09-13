package com.github.hexagonoframework.example.webapplication.interfaces.bookmark.rest.v1;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/bookmarks/v1/bookmark")
public class BookmarkRest {

    @Context
    private UriInfo uriInfo;

    @Inject
    private CreateRestAdapter createRestAdapter;

    @Inject
    private GetByIdRestAdapter getByIdRestAdapter;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context UriInfo uriInfo, CreateBookmarkRequest request) {
        return createRestAdapter.create(uriInfo, request);
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") String id) {
        return getByIdRestAdapter.getById(id);
    }

}
