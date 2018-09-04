package com.github.hexagonoframework.example.application.command;

import com.github.hexagonoframework.example.domain.bookmark.Bookmark;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkDescription;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkName;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkRegistrationService;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkRepository;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkURL;

public class RegisterBookmark {

    private BookmarkRepository repository;

    public RegisterBookmark(BookmarkRepository repository) {
        super();
        this.repository = repository;
    }

    /**
     * Register bookmark
     * @param data
     * @return Bookmark ID registered
     */
    public BookmarkId execute(BookmarkRegistrationData data) {
        BookmarkName name = new BookmarkName(data.name);
        BookmarkDescription description = new BookmarkDescription(data.description);
        BookmarkURL url = new BookmarkURL(data.url);
        
        BookmarkRegistrationService service = new BookmarkRegistrationService(repository);
        Bookmark bookmark = service.register(name, description, url);
        repository.store(bookmark);
        return bookmark.getId();
    }
    
}
