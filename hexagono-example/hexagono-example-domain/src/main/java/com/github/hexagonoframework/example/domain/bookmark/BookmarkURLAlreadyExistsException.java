package com.github.hexagonoframework.example.domain.bookmark;

import java.text.MessageFormat;

import com.github.hexagonoframework.example.domain.DomainException;

public class BookmarkURLAlreadyExistsException extends DomainException {

    private static final long serialVersionUID = 1L;
    private static final String PATTERN = "Bookmark with URL {0} already exists";
    private final BookmarkURL url;

    public BookmarkURLAlreadyExistsException(BookmarkURL alreadyExistingURL) {
        super(MessageFormat.format(PATTERN, alreadyExistingURL.getValue()));
        this.url = alreadyExistingURL;
    }

    public BookmarkURL getAlreadyExistingURL() {
        return url;
    }
}
