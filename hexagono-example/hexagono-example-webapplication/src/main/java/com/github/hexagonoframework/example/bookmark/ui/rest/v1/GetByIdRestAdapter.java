package com.github.hexagonoframework.example.bookmark.ui.rest.v1;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.github.hexagonoframework.example.bookmark.BookmarkId;
import com.github.hexagonoframework.example.bookmark.query.BookmarkDto;
import com.github.hexagonoframework.example.bookmark.query.BookmarkQueryExecutor;

@Dependent
public class GetByIdRestAdapter {
    
    private BookmarkQueryExecutor executor;
    
    @Inject
    public GetByIdRestAdapter(BookmarkQueryExecutor executor) {
        this.executor = executor;
    }

    public Response getById(@PathParam("id") String id) {
        BookmarkDto bookmark = executor.retrieve(new BookmarkId(id));
        if (null == bookmark) {
            throw new NotFoundException();
        }

        BookmarkRestDto dto = new BookmarkRestDto(id,
                new BookmarkDataRestDto(bookmark.name, bookmark.description, bookmark.url));
        
        return Response.ok(dto).build();
    }

}
