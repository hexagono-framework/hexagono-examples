package com.github.hexagonoframework.example.bookmark;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Test;

import com.github.hexagonoframework.example.bookmark.BookmarkId;

public class BookmarkIdTest {

    BookmarkId id;

    @Test
    public void generation() {
        id = BookmarkId.generate();
        assertNotNull(id.value());
    }
    
    @Test
    public void fromValue() {
        String uuid = UUID.randomUUID().toString();
        id = new BookmarkId(uuid);
        assertEquals(uuid, id.value());
    }
    
}
