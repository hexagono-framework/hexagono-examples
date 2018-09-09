package com.github.hexagonoframework.example.domain.bookmark;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BookmarkURLTest {

    String value = "Bookmark URL";
    BookmarkURL url;

    @Test
    public void creation() {
        url = new BookmarkURL(value);
        assertEquals(value, url.getValue());
    }

    @Test
    public void sameHashCode() {
        BookmarkURL one = new BookmarkURL(value);
        BookmarkURL two = new BookmarkURL(value);
        assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    public void differentHashCode() {
        BookmarkURL urlOne = new BookmarkURL("Bookmark URL One");
        BookmarkURL urlTwo = new BookmarkURL("Bookmark URL Two");
        assertNotEquals(urlOne.hashCode(), urlTwo.hashCode());
    }

    @Test
    public void equalsWithNull() {
        url = new BookmarkURL(value);
        assertFalse(url.equals(null));
    }

    @Test
    public void equalsWithSameObject() {
        url = new BookmarkURL(value);
        assertTrue(url.equals(url));
    }

    @Test
    public void equalsWithSameValue() {
        url = new BookmarkURL(value);
        assertTrue(url.equals(new BookmarkURL(value)));
    }

    @Test
    public void equalsWithDifferentValue() {
        url = new BookmarkURL(value);
        assertFalse(url.equals(new BookmarkURL("Different Bookmark URL")));
    }

    @Test
    public void equalsWithOtherType() {
        url = new BookmarkURL(value);
        assertFalse(url.equals(new Object()));
    }

}
