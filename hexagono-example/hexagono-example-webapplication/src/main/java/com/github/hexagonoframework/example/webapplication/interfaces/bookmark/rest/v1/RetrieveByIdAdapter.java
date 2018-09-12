package com.github.hexagonoframework.example.webapplication.interfaces.bookmark.rest.v1;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.github.hexagonoframework.example.domain.bookmark.Bookmark;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkRepository;

@Dependent
@Stateless
public class RetrieveByIdAdapter {

    @Inject
    private BookmarkRepository repository;

    public BookmarkRestDto execute(String id) {
        Bookmark bookmark = repository.retrieve(new BookmarkId(id));
        return new BookmarkRestDto(id, bookmark.getName().getValue(), bookmark.getDescription().getValue(),
                bookmark.getURL().getValue());
    }

}
