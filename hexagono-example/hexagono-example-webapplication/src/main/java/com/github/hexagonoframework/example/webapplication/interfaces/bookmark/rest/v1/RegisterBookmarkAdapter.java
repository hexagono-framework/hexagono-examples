package com.github.hexagonoframework.example.webapplication.interfaces.bookmark.rest.v1;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.github.hexagonoframework.example.application.command.BookmarkRegistrationData;
import com.github.hexagonoframework.example.application.command.RegisterBookmark;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkRepository;

@Dependent
@Stateless
public class RegisterBookmarkAdapter {

    @Inject
    private BookmarkRepository repository;
    
    public BookmarkId execute(CreateBookmarkRequest request) {
        BookmarkRegistrationData data = new BookmarkRegistrationData(request.name, request.description, request.url);
        RegisterBookmark command = new RegisterBookmark(repository);
        return command.execute(data);
    }
    
}
