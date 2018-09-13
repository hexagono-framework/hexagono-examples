package com.github.hexagonoframework.example.webapplication.interfaces.bookmark.rest.v1;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.github.hexagonoframework.example.domain.bookmark.Bookmark;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkRepository;

@Dependent
public class GetByIdRestAdapter {

    @Inject
    private BookmarkRepository bookmarkRepository;
    
    public Response getById(@PathParam("id") String id) {
        Bookmark bookmark = bookmarkRepository.retrieve(new BookmarkId(id));
        if (null == bookmark) {
            throw new NotFoundException();
        }
        
        BookmarkRestDto dto = new BookmarkRestDto(id, bookmark.getName().getValue(),
                bookmark.getDescription().getValue(), bookmark.getURL().getValue());
        return Response.ok(dto).build();
    }
    
}
