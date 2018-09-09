package com.github.hexagonoframework.example.domain.bookmark;

import java.util.Objects;

public class BookmarkURL {

    private final String value;

    public BookmarkURL(String value) {
        this.value = value;
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
        if (!(obj instanceof BookmarkURL)) {
            return false;
        }
        BookmarkURL other = (BookmarkURL) obj;
        return Objects.equals(this.value, other.value);
    }

}
