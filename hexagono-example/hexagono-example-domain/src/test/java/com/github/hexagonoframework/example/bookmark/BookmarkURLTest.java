package com.github.hexagonoframework.example.bookmark;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.hexagonoframework.example.bookmark.BookmarkURL;

public class BookmarkURLTest {

    String value = "Bookmark URL";
    BookmarkURL url;

    @Test
    public void creation() {
        url = new BookmarkURL(value);
        assertEquals(value, url.value());
    }

}
