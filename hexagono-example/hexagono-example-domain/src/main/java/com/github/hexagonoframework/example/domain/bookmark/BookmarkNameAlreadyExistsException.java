package com.github.hexagonoframework.example.domain.bookmark;

import java.text.MessageFormat;

import com.github.hexagonoframework.example.domain.DomainException;

public class BookmarkNameAlreadyExistsException extends DomainException {

    private static final long serialVersionUID = 1L;
    private static final String PATTERN = "Bookmark with name {0} already exists";
    private final BookmarkName name;

    public BookmarkNameAlreadyExistsException(BookmarkName alreadyExistingName) {
        super(MessageFormat.format(PATTERN, alreadyExistingName.getValue()));
        this.name = alreadyExistingName;
    }

    public BookmarkName getAlreadyExistingName() {
        return name;
    }
}
