package com.github.hexagonoframework.example.bookmark;

interface BookmarkRepository {

    void store(Bookmark bookmark);
    
    /**
     * Retrive Bookmark by Id or null
     * @param id Bookmark Id
     * @return Bookmark
     */
    Bookmark retrieve(BookmarkId id);
    
    Bookmark findByName(BookmarkName name);
    
    Bookmark findByUrl(BookmarkURL url);
        

}
