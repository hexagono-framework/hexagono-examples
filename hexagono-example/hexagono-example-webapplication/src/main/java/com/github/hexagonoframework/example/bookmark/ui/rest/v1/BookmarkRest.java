package com.github.hexagonoframework.example.bookmark.ui.rest.v1;

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
    private UpdateRestAdapter updateRestAdapter;

    @Inject
    private GetByIdRestAdapter getByIdRestAdapter;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context UriInfo uriInfo, BookmarkDataRestDto request) {
        return createRestAdapter.create(uriInfo, request);
    }
    
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, BookmarkDataRestDto request) {
        return updateRestAdapter.update(id, request);
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") String id) {
        return getByIdRestAdapter.getById(id);
    }

}
