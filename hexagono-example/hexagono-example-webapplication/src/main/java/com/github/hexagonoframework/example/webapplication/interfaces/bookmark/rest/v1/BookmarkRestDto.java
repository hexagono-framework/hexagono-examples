package com.github.hexagonoframework.example.webapplication.interfaces.bookmark.rest.v1;

public class BookmarkRestDto {

    public final String id;
    public final String name;
    public final String description;
    public final String url;
    
    public BookmarkRestDto(String id, String name, String description, String url) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
    }
    
}
