package com.github.hexagonoframework.example.bookmark;

import java.util.ArrayList;
import java.util.List;

import com.github.hexagonoframework.example.bookmark.Bookmark;
import com.github.hexagonoframework.example.bookmark.BookmarkRepository;
import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

public class BookmarkRepositoryMaker {

    public static final Property<BookmarkRepository, List<Bookmark>> bookmarks = new Property<>();
    
    public static final Instantiator<BookmarkRepository> FakeBookmarkRepository = new Instantiator<BookmarkRepository>() {
        
        @Override
        public BookmarkRepository instantiate(PropertyLookup<BookmarkRepository> lookup) {
            BookmarkRepository repository = new FakeBookmarkRepository(lookup.valueOf(bookmarks, new ArrayList<>()));
            return repository;
        }
    };
    
    
    
}
