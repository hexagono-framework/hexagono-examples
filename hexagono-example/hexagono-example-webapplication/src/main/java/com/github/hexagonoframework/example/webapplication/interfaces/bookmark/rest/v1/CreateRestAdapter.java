package com.github.hexagonoframework.example.webapplication.interfaces.bookmark.rest.v1;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.github.hexagonoframework.example.application.command.BookmarkData;
import com.github.hexagonoframework.example.application.command.RegisterBookmark.RegisterBookmarkException;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.webapplication.application.command.RegisterBookmarkAdapter;
import com.github.hexagonoframework.example.webapplication.infrastructure.jaxrs.ErrorResponseEntity;

@Dependent
public class CreateRestAdapter {

    @Inject
    private RegisterBookmarkAdapter registerBookmarkAdapter;

    public Response create(UriInfo uriInfo, BookmarkDataRequest request) {
        BookmarkId bookmarkId;
        try {
            BookmarkData data = new BookmarkData(request.name, request.description, request.url);
            bookmarkId = registerBookmarkAdapter.execute(data);
        } catch (RegisterBookmarkException e) {
            throw new BadRequestException(createErrorResponse(e), e);
        }

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(bookmarkId.getValue());
        return Response.created(uriBuilder.build()).build();
    }

    private Response createErrorResponse(RegisterBookmarkException e) {
        ErrorResponseEntity entity;
        switch (e.getErrorCode()) {
        case INVALID_BOOKMARK_NAME:
        case INVALID_BOOKMARK_DESCRIPTION:
        case INVALID_BOOKMARK_URL:
            entity = new ErrorResponseEntity("INVALID_REQUEST", e.getMessage());
        default:
            entity = new ErrorResponseEntity(e.getErrorCode().name(), e.getMessage());
            break;
        }

        return Response.status(BAD_REQUEST).entity(entity).build();
    }

}
