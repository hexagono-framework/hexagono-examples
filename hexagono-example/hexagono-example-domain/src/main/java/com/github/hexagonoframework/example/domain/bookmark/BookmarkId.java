package com.github.hexagonoframework.example.domain.bookmark;

import java.util.UUID;

public class BookmarkId {

    private final String value;

    public BookmarkId(String value) {
        this.value = value;
    }

    public static BookmarkId generate() {
        return new BookmarkId(UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }

}
