package com.github.hexagonoframework.example.application.command;

import static com.github.hexagonoframework.example.application.command.RegisterBookmarkException.ErrorCode.*;

import com.github.hexagonoframework.example.domain.bookmark.Bookmark;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkDescription;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkName;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkNameAlreadyExistsException;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkRegistrationService;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkRepository;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkURL;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkURLAlreadyExistsException;

public class RegisterBookmark {

    private final BookmarkRepository repository;
    private final BookmarkRegistrationService service;

    public RegisterBookmark(BookmarkRepository repository) {
        super();
        this.repository = repository;
        this.service = new BookmarkRegistrationService(repository);
    }

    /**
     * Register bookmark
     * @param data
     * @return Bookmark ID registered
     * @throws RegisterBookmarkException Exception
     */
    public BookmarkId execute(BookmarkRegistrationData data) {
        if (null == data.name) {
            throw new RegisterBookmarkException(INVALID_BOOKMARK_NAME, "Invalid bookmark name");
        }
        
        if (null == data.description) {
            throw new RegisterBookmarkException(INVALID_BOOKMARK_DESCRIPTION, "Invalid bookmark description");
        }
        
        if (null == data.url) {
            throw new RegisterBookmarkException(INVALID_BOOKMARK_URL, "Invalid bookmark URL");
        }
        
        BookmarkName name = new BookmarkName(data.name);
        BookmarkDescription description = new BookmarkDescription(data.description);
        BookmarkURL url = new BookmarkURL(data.url);
        
        Bookmark bookmark;
        try {
            bookmark = service.register(name, description, url);
        } catch (BookmarkNameAlreadyExistsException e) {
            throw new RegisterBookmarkException(BOOKMARK_NAME_ALREADY_EXISTS, e);
        } catch (BookmarkURLAlreadyExistsException e) {
            throw new RegisterBookmarkException(BOOKMARK_URL_ALREADY_EXISTS, e);
        }
        
        repository.store(bookmark);
        return bookmark.getId();
    }
    
}
