package com.github.hexagonoframework.example.bookmark;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.hexagonoframework.example.bookmark.BookmarkName;

public class BookmarkNameTest {

    String value = "Bookmark Name";
    BookmarkName name;

    @Test
    public void creation() {
        name = new BookmarkName(value);
        assertEquals(value, name.value());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyValue() {
        new BookmarkName("");
    }
    
}
