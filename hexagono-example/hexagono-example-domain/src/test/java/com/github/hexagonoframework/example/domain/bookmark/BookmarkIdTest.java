package com.github.hexagonoframework.example.domain.bookmark;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

public class BookmarkIdTest {

    BookmarkId id;

    @Test
    public void generation() {
        id = BookmarkId.generate();
        assertNotNull(id.getValue());
    }
    
    @Test
    public void fromValue() {
        String uuid = UUID.randomUUID().toString();
        id = new BookmarkId(uuid);
        assertEquals(uuid, id.getValue());
    }
    
    @Test
    public void sameHashCode() {
        String value = UUID.randomUUID().toString();
        BookmarkId idOne = new BookmarkId(value);
        BookmarkId idTwo = new BookmarkId(value);
        assertEquals(idOne.hashCode(), idTwo.hashCode());
    }
    
    @Test
    public void differentHashCode() {
        BookmarkId idOne = new BookmarkId(UUID.randomUUID().toString());
        BookmarkId idTwo = new BookmarkId(UUID.randomUUID().toString());
        assertNotEquals(idOne.hashCode(), idTwo.hashCode());
    }
    
    @Test
    public void equalsWithNull() {
        id = BookmarkId.generate();
        assertFalse(id.equals(null));
    }
    
    @Test
    public void equalsWithSameObject() {
        id = BookmarkId.generate();
        assertTrue(id.equals(id));
    }
    
    @Test
    public void equalsWithSameValue() {
        id = BookmarkId.generate();
        assertTrue(id.equals(new BookmarkId(id.getValue())));
    }
    
    @Test
    public void equalsWithDifferentValue() {
        id = BookmarkId.generate();
        assertFalse(id.equals(BookmarkId.generate()));
    }
    
    @Test
    public void equalsWithOtherType() {
        id = BookmarkId.generate();
        assertFalse(id.equals(new Object()));
    }
    
}
