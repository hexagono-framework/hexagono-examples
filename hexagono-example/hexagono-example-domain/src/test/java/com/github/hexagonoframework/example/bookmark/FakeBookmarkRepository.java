package com.github.hexagonoframework.example.bookmark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hexagonoframework.example.bookmark.Bookmark;
import com.github.hexagonoframework.example.bookmark.BookmarkId;
import com.github.hexagonoframework.example.bookmark.BookmarkName;
import com.github.hexagonoframework.example.bookmark.BookmarkRepository;
import com.github.hexagonoframework.example.bookmark.BookmarkURL;

public class FakeBookmarkRepository implements BookmarkRepository {

    private final Map<BookmarkId, Bookmark> bookmarks;

    public FakeBookmarkRepository() {
        bookmarks = new HashMap<>();
    }
    
    public FakeBookmarkRepository(List<Bookmark> values) {
        this();
        for (Bookmark bookmark : values) {
            bookmarks.put(bookmark.getId(), bookmark);
        }
    }
    
    @Override
    public void store(Bookmark bookmark) {
        bookmarks.put(bookmark.getId(), bookmark);
    }

    @Override
    public Bookmark retrieve(BookmarkId id) {
        return bookmarks.get(id);
    }

    @Override
    public Bookmark findByName(BookmarkName name) {
        for (Bookmark bookmark : bookmarks.values()) {
            if (name.equals(bookmark.getName())) {
                return bookmark;
            }
        }

        return null;
    }

    @Override
    public Bookmark findByUrl(BookmarkURL url) {
        for (Bookmark bookmark : bookmarks.values()) {
            if (url.equals(bookmark.getURL())) {
                return bookmark;
            }
        }

        return null;
    }

}
