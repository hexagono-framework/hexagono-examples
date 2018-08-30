package com.github.hexagonoframework.example.domain.bookmark;

public class Bookmark {

    private final BookmarkId id;
    private final BookmarkName name;
    private final BookmarkDescription description;
    private final BookmarkURL url;

    public Bookmark(BookmarkId id, BookmarkName name, BookmarkDescription description, BookmarkURL url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public BookmarkId getId() {
        return id;
    }

    public BookmarkName getName() {
        return name;
    }

    public BookmarkDescription getDescription() {
        return description;
    }

    public BookmarkURL getUrl() {
        return url;
    }
}
