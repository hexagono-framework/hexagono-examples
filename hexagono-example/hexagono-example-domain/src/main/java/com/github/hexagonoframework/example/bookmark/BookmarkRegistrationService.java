package com.github.hexagonoframework.example.bookmark;

class BookmarkRegistrationService {

    private BookmarkRepository repository;

    BookmarkRegistrationService(BookmarkRepository repository) {
        super();
        this.repository = repository;
    }
    
    /**
     * Register a new bookmark
     * @param name Name
     * @param description Description
     * @param url URL
     * @return new bookmark created
     * @throws BookmarkNameAlreadyExistsException if name already exists
     * @throws BookmarkURLAlreadyExistsException if URL already exists
     */
    Bookmark register(BookmarkName name, BookmarkDescription description, BookmarkURL url) {
        if (null != repository.findByName(name)) {
            throw new BookmarkNameAlreadyExistsException(name);
        }
        
        if (null != repository.findByUrl(url)) {
            throw new BookmarkURLAlreadyExistsException(url);
        }
        
        BookmarkId id = BookmarkId.generate();
        return new Bookmark(id, name, description, url);
    }
    
}
