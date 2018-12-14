package com.github.hexagonoframework.example.bookmark;

import java.text.MessageFormat;

import com.github.hexagonoframework.DomainException;

class BookmarkNameAlreadyExistsException extends DomainException {

    private static final long serialVersionUID = 1L;
    private static final String PATTERN = "Bookmark with name {0} already exists";
    private final BookmarkName name;

    BookmarkNameAlreadyExistsException(BookmarkName alreadyExistingName) {
        super(MessageFormat.format(PATTERN, alreadyExistingName.value()));
        this.name = alreadyExistingName;
    }

    BookmarkName getAlreadyExistingName() {
        return name;
    }
}
