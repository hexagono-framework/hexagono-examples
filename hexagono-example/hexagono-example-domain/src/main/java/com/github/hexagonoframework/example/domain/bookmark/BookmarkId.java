package com.github.hexagonoframework.example.domain.bookmark;

import java.util.Objects;
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

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BookmarkId)) {
            return false;
        }
        BookmarkId other = (BookmarkId) obj;
        return Objects.equals(this.value, other.value);
    }

}
