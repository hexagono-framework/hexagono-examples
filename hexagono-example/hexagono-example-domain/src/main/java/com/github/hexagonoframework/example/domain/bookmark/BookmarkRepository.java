package com.github.hexagonoframework.example.domain.bookmark;

public interface BookmarkRepository {

    void store(Bookmark bookmark);
    
    Bookmark retrieve(BookmarkId id);
    
    Bookmark findByName(BookmarkName name);
    
    Bookmark findByUrl(BookmarkURL url);
        

}
