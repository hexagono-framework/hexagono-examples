package com.github.hexagonoframework.example.bookmark;

import java.util.LinkedHashMap;
import java.util.Map;

import com.github.hexagonoframework.example.bookmark.Bookmark;
import com.github.hexagonoframework.example.bookmark.BookmarkId;
import com.github.hexagonoframework.example.bookmark.BookmarkName;
import com.github.hexagonoframework.example.bookmark.BookmarkRepository;
import com.github.hexagonoframework.example.bookmark.BookmarkURL;

public class InMemoryBookmarkRepository implements BookmarkRepository {

    private final Map<BookmarkId, Bookmark> bookmarks = new LinkedHashMap<>();
    
    @Override
    public void store(Bookmark bookmark) {
        bookmarks.put(bookmark.getId(), bookmark);
    }

    @Override
    public Bookmark retrieve(BookmarkId id) {
        return bookmarks.getOrDefault(id, null);
    }

    @Override
    public Bookmark findByName(BookmarkName name) {
        return bookmarks.values().stream()
                .filter(bookmark -> bookmark.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Bookmark findByUrl(BookmarkURL url) {
        return bookmarks.values().stream()
                .filter(bookmark -> bookmark.getURL().equals(url))
                .findFirst()
                .orElse(null);
    }

}
