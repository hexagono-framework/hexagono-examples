package com.github.hexagonoframework.example.webapplication.interfaces.bookmark.rest.v1;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.hexagonoframework.example.application.command.BookmarkRegistrationData;
import com.github.hexagonoframework.example.application.command.RegisterBookmark;
import com.github.hexagonoframework.example.application.command.RegisterBookmarkException;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkRepository;
import com.github.hexagonoframework.example.webapplication.infrastructure.jaxrs.ErrorResponseEntity;

@Path("/bookmarks/v1/bookmark")
public class BookmarkRest {

    private enum ErrorCodes {
        BOOKMARK_NAME_ALREADY_EXISTS, BOOKMARK_URL_ALREADY_EXISTS, INVALID_REQUEST
    }

    @Inject
    private BookmarkRepository repository;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CreateBookmarkRequest request) {
        BookmarkRegistrationData data = new BookmarkRegistrationData(request.name, request.description, request.url);
        RegisterBookmark command = new RegisterBookmark(repository);

        BookmarkId bookmarkId;
        try {
            bookmarkId = command.execute(data);
        } catch (RegisterBookmarkException e) {
            throw new BadRequestException(createErrorResponse(e), e);
        }

        return Response.ok(bookmarkId.getValue()).build();
    }

    private Response createErrorResponse(RegisterBookmarkException e) {
        ErrorResponseEntity entity;
        switch (e.error()) {
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
