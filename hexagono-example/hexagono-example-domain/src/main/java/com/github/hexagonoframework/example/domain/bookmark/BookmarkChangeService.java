package com.github.hexagonoframework.example.domain.bookmark;

public class BookmarkChangeService {

    private BookmarkRepository repository;

    public BookmarkChangeService(BookmarkRepository repository) {
        super();
        this.repository = repository;
    }
    
    /**
     * Change bookmark data
     * @param id Bookmark ID
     * @param name Name
     * @param description Description
     * @param url URL
     * @return new bookmark updated
     * @throws BookmarkNameAlreadyExistsException if name already exists
     * @throws BookmarkURLAlreadyExistsException if URL already exists
     */
    public Bookmark change(BookmarkId id, BookmarkName name, BookmarkDescription description, BookmarkURL url) {
        checkName(id, name);
        checkURL(id, url);
        return new Bookmark(id, name, description, url);
    }
    
    private void checkName(BookmarkId id, BookmarkName name) {
        Bookmark bookmark = repository.findByName(name);
        if (null != bookmark && !bookmark.getId().equals(id)) {
            throw new BookmarkNameAlreadyExistsException(name);
        }
    }
    
    private void checkURL(BookmarkId id, BookmarkURL url) {
        Bookmark bookmark = repository.findByUrl(url);
        if (null != bookmark && !bookmark.getId().equals(id)) {
            throw new BookmarkURLAlreadyExistsException(url);
        }
    }
    
    
    
}
