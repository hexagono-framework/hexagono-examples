package com.github.hexagonoframework.example.bookmark;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.hexagonoframework.example.bookmark.BookmarkDescription;

public class BookmarkDescriptionTest {

    String value = "Bookmark Description";
    BookmarkDescription description;

    @Test
    public void creation() {
        description = new BookmarkDescription(value);
        assertEquals(value, description.value());
    }
    
}
