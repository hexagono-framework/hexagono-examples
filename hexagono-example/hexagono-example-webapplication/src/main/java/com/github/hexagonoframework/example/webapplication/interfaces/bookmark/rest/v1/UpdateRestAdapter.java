package com.github.hexagonoframework.example.webapplication.interfaces.bookmark.rest.v1;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import com.github.hexagonoframework.example.application.command.BookmarkData;
import com.github.hexagonoframework.example.application.command.UpdateBookmark.UpdateBookmarkException;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.webapplication.application.command.UpdateBookmarkAdapter;
import com.github.hexagonoframework.example.webapplication.infrastructure.jaxrs.ErrorResponseEntity;

@Dependent
public class UpdateRestAdapter {

    private enum ErrorCodes {
        BOOKMARK_NAME_ALREADY_EXISTS, 
        BOOKMARK_URL_ALREADY_EXISTS,
        BOOKMARK_DOES_NOT_EXIST,
        INVALID_REQUEST
    }
    
    @Inject
    private UpdateBookmarkAdapter updateBookmarkAdapter;
    
    public Response update(String id, BookmarkDataRequest request) {
        try {
            BookmarkData data = new BookmarkData(request.name, request.description, request.url);
            updateBookmarkAdapter.execute(new BookmarkId(id), data);
        } catch (UpdateBookmarkException e) {
            throw new BadRequestException(createErrorResponse(e), e);
        }

        return Response.noContent().build();
    }
    
    private Response createErrorResponse(UpdateBookmarkException e) {
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
