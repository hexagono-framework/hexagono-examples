package com.github.hexagonoframework.example.bookmark;

import java.util.UUID;

import com.github.hexagonoframework.core.ValueObject;

public class BookmarkId extends ValueObject<String> {

    public BookmarkId(String value) {
        super(value);
    }

    public static BookmarkId generate() {
        return new BookmarkId(UUID.randomUUID().toString());
    }

}
