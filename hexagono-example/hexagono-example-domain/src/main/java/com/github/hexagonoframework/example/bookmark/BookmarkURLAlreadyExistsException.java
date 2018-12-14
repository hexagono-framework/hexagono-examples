package com.github.hexagonoframework.example.bookmark;

import java.text.MessageFormat;

import com.github.hexagonoframework.DomainException;

class BookmarkURLAlreadyExistsException extends DomainException {

    private static final long serialVersionUID = 1L;
    private static final String PATTERN = "Bookmark with URL {0} already exists";
    private final BookmarkURL url;

    BookmarkURLAlreadyExistsException(BookmarkURL alreadyExistingURL) {
        super(MessageFormat.format(PATTERN, alreadyExistingURL.value()));
        this.url = alreadyExistingURL;
    }

    BookmarkURL getAlreadyExistingURL() {
        return url;
    }
}
