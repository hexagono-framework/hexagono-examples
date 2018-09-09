package com.github.hexagonoframework.example.domain.bookmark;

import java.util.Objects;

public class BookmarkDescription {

    private final String value;

    public BookmarkDescription(String value) {
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
        if (!(obj instanceof BookmarkDescription)) {
            return false;
        }
        BookmarkDescription other = (BookmarkDescription) obj;
        return Objects.equals(this.value, other.value);
    }
    

}
