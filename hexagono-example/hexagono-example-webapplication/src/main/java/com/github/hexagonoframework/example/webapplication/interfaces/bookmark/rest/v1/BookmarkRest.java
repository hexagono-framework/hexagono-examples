package com.github.hexagonoframework.example.webapplication.interfaces.bookmark.rest.v1;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.github.hexagonoframework.example.application.command.RegisterBookmark.RegisterBookmarkException;
import com.github.hexagonoframework.example.application.command.RegisterBookmark.RegistrationData;
import com.github.hexagonoframework.example.domain.bookmark.Bookmark;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkRepository;
import com.github.hexagonoframework.example.webapplication.application.command.RegisterBookmarkAdapter;
import com.github.hexagonoframework.example.webapplication.infrastructure.jaxrs.ErrorResponseEntity;

@Path("/bookmarks/v1/bookmark")
public class BookmarkRest {

    private enum ErrorCodes {
        BOOKMARK_NAME_ALREADY_EXISTS, 
        BOOKMARK_URL_ALREADY_EXISTS, 
        INVALID_REQUEST
    }

    @Context
    private UriInfo uriInfo;

    @Inject
    private RegisterBookmarkAdapter registerBookmarkAdapter;

    @Inject
    private BookmarkRepository bookmarkRepository;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CreateBookmarkRequest request) {
        BookmarkId bookmarkId;
        try {
            RegistrationData data = new RegistrationData(request.name, request.description, request.url);
            bookmarkId = registerBookmarkAdapter.execute(data);
        } catch (RegisterBookmarkException e) {
            throw new BadRequestException(createErrorResponse(e), e);
        }

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(bookmarkId.getValue());
        return Response.created(uriBuilder.build()).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") String id) {
        Bookmark bookmark = bookmarkRepository.retrieve(new BookmarkId(id));
        if (null == bookmark) {
            throw new NotFoundException();
        }
        
        BookmarkRestDto dto = new BookmarkRestDto(id, bookmark.getName().getValue(),
                bookmark.getDescription().getValue(), bookmark.getURL().getValue());
        return Response.ok(dto).build();
    }

    private Response createErrorResponse(RegisterBookmarkException e) {
        ErrorResponseEntity entity;
        switch (e.getErrorCode()) {
        case BOOKMARK_NAME_ALREADY_EXISTS:
            entity = new ErrorResponseEntity(ErrorCodes.BOOKMARK_NAME_ALREADY_EXISTS.name(), e.getMessage());
            break;
        case BOOKMARK_URL_ALREADY_EXISTS:
            entity = new ErrorResponseEntity(ErrorCodes.BOOKMARK_URL_ALREADY_EXISTS.name(), e.getMessage());
            break;
        default:
            entity = new ErrorResponseEntity(ErrorCodes.INVALID_REQUEST.name(), e.getMessage());
            break;
        }

        return Response.status(BAD_REQUEST).entity(entity).build();
    }

}
