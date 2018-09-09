package com.github.hexagonoframework.example.domain.bookmark;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

public class BookmarkMaker {

    public static final Property<Bookmark, BookmarkId> id = new Property<>();
    public static final Property<Bookmark, BookmarkName> name = new Property<>();
    public static final Property<Bookmark, BookmarkDescription> description = new Property<>();
    public static final Property<Bookmark, BookmarkURL> url = new Property<>();

    public static final Instantiator<Bookmark> Bookmark = new Instantiator<Bookmark>() {

        @Override
        public Bookmark instantiate(PropertyLookup<Bookmark> lookup) {
            Bookmark bookmark = new Bookmark(
                    lookup.valueOf(id, BookmarkId.generate()), 
                    lookup.valueOf(name, new BookmarkName("Default Bookmark Name")), 
                    lookup.valueOf(description, new BookmarkDescription("Default Bookmark Description")), 
                    lookup.valueOf(url, new BookmarkURL("http://default.bookmark.url")));
            
            return bookmark;
        }
    };

}
