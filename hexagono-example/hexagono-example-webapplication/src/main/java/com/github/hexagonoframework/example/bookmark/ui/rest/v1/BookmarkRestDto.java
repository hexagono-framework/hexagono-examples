package com.github.hexagonoframework.example.bookmark.ui.rest.v1;

import org.codehaus.jackson.annotate.JsonUnwrapped;

public class BookmarkRestDto {

    public final String id;
    
    @JsonUnwrapped
    public final BookmarkDataRestDto data;

    public BookmarkRestDto(String id, BookmarkDataRestDto data) {
        super();
        this.id = id;
        this.data = data;
    }

}
