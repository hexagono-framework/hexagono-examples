package com.github.hexagonoframework.example.domain.bookmark;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BookmarkDescriptionTest {

    String value = "Bookmark Description";
    BookmarkDescription description;

    @Test
    public void creation() {
        description = new BookmarkDescription(value);
        assertEquals(value, description.getValue());
    }
    
    @Test
    public void sameHashCode() {
        BookmarkDescription descriptionOne = new BookmarkDescription(value);
        BookmarkDescription descriptionTwo = new BookmarkDescription(value);
        assertEquals(descriptionOne.hashCode(), descriptionTwo.hashCode());
    }
    
    @Test
    public void differentHashCode() {
        BookmarkDescription descriptionOne = new BookmarkDescription("Bookmark Description One");
        BookmarkDescription descriptionTwo = new BookmarkDescription("Bookmark Description Two");
        assertNotEquals(descriptionOne.hashCode(), descriptionTwo.hashCode());
    }
    
    @Test
    public void equalsWithNull() {
        description = new BookmarkDescription(value);
        assertFalse(description.equals(null));
    }
    
    @Test
    public void equalsWithSameObject() {
        description = new BookmarkDescription(value);
        assertTrue(description.equals(description));
    }
    
    @Test
    public void equalsWithSameValue() {
        description = new BookmarkDescription(value);
        assertTrue(description.equals(new BookmarkDescription(value)));
    }
    
    @Test
    public void equalsWithDifferentValue() {
        description = new BookmarkDescription(value);
        assertFalse(description.equals(new BookmarkDescription("Different Bookmark Description")));
    }
    
    @Test
    public void equalsWithOtherType() {
        description = new BookmarkDescription(value);
        assertFalse(description.equals(new Object()));
    }
    
}
