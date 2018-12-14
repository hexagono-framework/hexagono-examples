package com.github.hexagonoframework.example.bookmark;

import com.github.hexagonoframework.core.ValueObject;

class BookmarkName extends ValueObject<String> {

    BookmarkName(String value) {
        super(value);
        
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Invalid value for bookmark name: " + value);
        }
    }

}
