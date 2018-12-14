package com.github.hexagonoframework.example.bookmark;

class Bookmark {

    private final BookmarkId id;
    private final BookmarkName name;
    private final BookmarkDescription description;
    private final BookmarkURL url;

    Bookmark(BookmarkId id, BookmarkName name, BookmarkDescription description, BookmarkURL url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
    }

    BookmarkId getId() {
        return id;
    }

    BookmarkName getName() {
        return name;
    }

    BookmarkDescription getDescription() {
        return description;
    }

    BookmarkURL getURL() {
        return url;
    }
}
