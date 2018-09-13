package com.github.hexagonoframework.example.webapplication.interfaces.bookmark.rest.v1;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.github.hexagonoframework.example.application.command.RegisterBookmark.RegisterBookmarkException;
import com.github.hexagonoframework.example.application.command.RegisterBookmark.RegistrationData;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.webapplication.application.command.RegisterBookmarkAdapter;
import com.github.hexagonoframework.example.webapplication.infrastructure.jaxrs.ErrorResponseEntity;

@Dependent
public class CreateRestAdapter {

    private enum ErrorCodes {
        BOOKMARK_NAME_ALREADY_EXISTS, 
        BOOKMARK_URL_ALREADY_EXISTS, 
        INVALID_REQUEST
    }
    
    @Inject
    private RegisterBookmarkAdapter registerBookmarkAdapter;
    
    public Response create(UriInfo uriInfo, CreateBookmarkRequest request) {
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
