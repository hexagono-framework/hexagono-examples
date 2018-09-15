package com.github.hexagonoframework.example.application.command;

import static com.github.hexagonoframework.example.application.command.RegisterBookmark.ErrorCode.BOOKMARK_NAME_ALREADY_EXISTS;
import static com.github.hexagonoframework.example.application.command.RegisterBookmark.ErrorCode.BOOKMARK_URL_ALREADY_EXISTS;
import static com.github.hexagonoframework.example.application.command.RegisterBookmark.ErrorCode.INVALID_BOOKMARK_DESCRIPTION;
import static com.github.hexagonoframework.example.application.command.RegisterBookmark.ErrorCode.INVALID_BOOKMARK_NAME;
import static com.github.hexagonoframework.example.application.command.RegisterBookmark.ErrorCode.INVALID_BOOKMARK_URL;

import com.github.hexagonoframework.example.application.ApplicationException;
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
    public BookmarkId execute(BookmarkData data) throws RegisterBookmarkException {
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
    
    public enum ErrorCode {
        INVALID_BOOKMARK_NAME,
        INVALID_BOOKMARK_DESCRIPTION,
        INVALID_BOOKMARK_URL,
        BOOKMARK_NAME_ALREADY_EXISTS,
        BOOKMARK_URL_ALREADY_EXISTS
    }
    
    public static class RegisterBookmarkException extends ApplicationException {

        private static final long serialVersionUID = 1L;
        private final ErrorCode errorCode;
        
        public RegisterBookmarkException(ErrorCode errorCode, String message) {
            super(message);
            this.errorCode = errorCode;
        }
        
        public RegisterBookmarkException(ErrorCode errorCode, Throwable throwable) {
            super(throwable.getMessage(), throwable);
            this.errorCode = errorCode;
        }
        
        public ErrorCode getErrorCode() {
            return errorCode;
        }
        
    }
    
}
