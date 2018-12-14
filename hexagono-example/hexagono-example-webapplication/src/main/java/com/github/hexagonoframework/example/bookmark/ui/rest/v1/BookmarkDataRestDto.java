package com.github.hexagonoframework.example.bookmark.ui.rest.v1;

public class BookmarkDataRestDto {

    public final String name;
    public final String description;
    public final String url;

    public BookmarkDataRestDto(String name, String description, String url) {
        super();
        this.name = name;
        this.description = description;
        this.url = url;
    }

}
