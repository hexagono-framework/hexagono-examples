package com.github.hexagonoframework.example.domain.bookmark;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BookmarkNameTest {

    String value = "Bookmark Name";
    BookmarkName name;

    @Test
    public void creation() {
        name = new BookmarkName(value);
        assertEquals(value, name.getValue());
    }
    
    @Test
    public void sameHashCode() {
        BookmarkName nameOne = new BookmarkName(value);
        BookmarkName nameTwo = new BookmarkName(value);
        assertEquals(nameOne.hashCode(), nameTwo.hashCode());
    }
    
    @Test
    public void differentHashCode() {
        BookmarkName nameOne = new BookmarkName("Bookmark Name One");
        BookmarkName nameTwo = new BookmarkName("Bookmark Name Two");
        assertNotEquals(nameOne.hashCode(), nameTwo.hashCode());
    }
    
    @Test
    public void equalsWithNull() {
        name = new BookmarkName(value);
        assertFalse(name.equals(null));
    }
    
    @Test
    public void equalsWithSameObject() {
        name = new BookmarkName(value);
        assertTrue(name.equals(name));
    }
    
    @Test
    public void equalsWithSameValue() {
        name = new BookmarkName(value);
        assertTrue(name.equals(new BookmarkName(value)));
    }
    
    @Test
    public void equalsWithDifferentValue() {
        name = new BookmarkName(value);
        assertFalse(name.equals(new BookmarkName("Different Bookmark Name")));
    }
    
    @Test
    public void equalsWithOtherType() {
        name = new BookmarkName(value);
        assertFalse(name.equals(new Object()));
    }
    
}
