package com.github.hexagonoframework.example.bookmark.ui.rest.v1;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.github.hexagonoframework.example.bookmark.BookmarkData;
import com.github.hexagonoframework.example.bookmark.BookmarkId;
import com.github.hexagonoframework.example.bookmark.RegisterBookmarkDecorator;
import com.github.hexagonoframework.example.bookmark.RegisterBookmark.RegisterBookmarkException;
import com.github.hexagonoframework.example.webapplication.infrastructure.jaxrs.ErrorResponseEntity;

@Dependent
public class CreateRestAdapter {

    @Inject
    private RegisterBookmarkDecorator registerBookmarkAdapter;

    public Response create(UriInfo uriInfo, BookmarkDataRestDto request) {
        BookmarkId bookmarkId;
        try {
            BookmarkData data = new BookmarkData(request.name, request.description, request.url);
            bookmarkId = registerBookmarkAdapter.execute(data);
        } catch (RegisterBookmarkException e) {
            throw new BadRequestException(createErrorResponse(e), e);
        }

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(bookmarkId.value());
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
