package com.github.hexagonoframework.example.bookmark;

import static com.github.hexagonoframework.example.bookmark.UpdateBookmark.ErrorCode.BOOKMARK_DOES_NOT_EXIST;
import static com.github.hexagonoframework.example.bookmark.UpdateBookmark.ErrorCode.BOOKMARK_NAME_ALREADY_EXISTS;
import static com.github.hexagonoframework.example.bookmark.UpdateBookmark.ErrorCode.BOOKMARK_URL_ALREADY_EXISTS;
import static com.github.hexagonoframework.example.bookmark.UpdateBookmark.ErrorCode.INVALID_BOOKMARK_DESCRIPTION;
import static com.github.hexagonoframework.example.bookmark.UpdateBookmark.ErrorCode.INVALID_BOOKMARK_ID;
import static com.github.hexagonoframework.example.bookmark.UpdateBookmark.ErrorCode.INVALID_BOOKMARK_NAME;
import static com.github.hexagonoframework.example.bookmark.UpdateBookmark.ErrorCode.INVALID_BOOKMARK_URL;

import com.github.hexagonoframework.ApplicationException;

public class UpdateBookmark {

    private final BookmarkRepository repository;
    private final BookmarkChangeService service;

    public UpdateBookmark(BookmarkRepository repository) {
        super();
        this.repository = repository;
        this.service = new BookmarkChangeService(repository);
    }

    /**
     * Update bookmark
     * @param bookmarkId Bookmark ID
     * @param data Bookmark data
     * @throws UpdateBookmarkException Exception
     */
    public void execute(BookmarkId bookmarkId, BookmarkData data) throws UpdateBookmarkException {
        if (null == bookmarkId) {
            throw new UpdateBookmarkException(INVALID_BOOKMARK_ID, "Invalid bookmark ID");
        }
        
        if (null == data.name) {
            throw new UpdateBookmarkException(INVALID_BOOKMARK_NAME, "Invalid bookmark name");
        }
        
        if (null == data.description) {
            throw new UpdateBookmarkException(INVALID_BOOKMARK_DESCRIPTION, "Invalid bookmark description");
        }
        
        if (null == data.url) {
            throw new UpdateBookmarkException(INVALID_BOOKMARK_URL, "Invalid bookmark URL");
        }
        
        BookmarkName name = new BookmarkName(data.name);
        BookmarkDescription description = new BookmarkDescription(data.description);
        BookmarkURL url = new BookmarkURL(data.url);
        
        if (null == repository.retrieve(bookmarkId)) {
            throw new UpdateBookmarkException(BOOKMARK_DOES_NOT_EXIST, "Bookmark ID does not exist");
        }
        
        Bookmark bookmark;
        try {
            bookmark = service.change(bookmarkId, name, description, url);
        } catch (BookmarkNameAlreadyExistsException e) {
            throw new UpdateBookmarkException(BOOKMARK_NAME_ALREADY_EXISTS, e);
        } catch (BookmarkURLAlreadyExistsException e) {
            throw new UpdateBookmarkException(BOOKMARK_URL_ALREADY_EXISTS, e);
        }
        
        repository.store(bookmark);
    }
    
    public enum ErrorCode {
        INVALID_BOOKMARK_ID,
        INVALID_BOOKMARK_NAME,
        INVALID_BOOKMARK_DESCRIPTION,
        INVALID_BOOKMARK_URL,
        BOOKMARK_DOES_NOT_EXIST,
        BOOKMARK_NAME_ALREADY_EXISTS,
        BOOKMARK_URL_ALREADY_EXISTS
    }
    
    public static class UpdateBookmarkException extends ApplicationException {

        private static final long serialVersionUID = 1L;
        private final ErrorCode errorCode;
        
        public UpdateBookmarkException(ErrorCode errorCode, String message) {
            super(message);
            this.errorCode = errorCode;
        }
        
        public UpdateBookmarkException(ErrorCode errorCode, Throwable throwable) {
            super(throwable.getMessage(), throwable);
            this.errorCode = errorCode;
        }
        
        public ErrorCode getErrorCode() {
            return errorCode;
        }
    }
}
