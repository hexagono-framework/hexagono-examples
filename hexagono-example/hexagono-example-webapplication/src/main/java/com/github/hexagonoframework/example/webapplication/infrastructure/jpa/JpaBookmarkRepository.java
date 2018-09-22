package com.github.hexagonoframework.example.webapplication.infrastructure.jpa;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.github.hexagonoframework.example.domain.bookmark.Bookmark;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkDescription;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkName;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkRepository;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkURL;

@Dependent
public class JpaBookmarkRepository implements BookmarkRepository {

    @Inject
    private BookmarkRepositoryDelegate delegate;
    
    @Override
    public void store(Bookmark bookmark) {
        JpaBookmark entity = Assembler.toEntity(bookmark);
        delegate.save(entity);
    }

    @Override
    public Bookmark retrieve(BookmarkId id) {
        JpaBookmark entity = delegate.findBy(id.getValue());
        if (null == entity) {
            return null;
        }
        
        return Assembler.fromEntity(entity);
    }

    @Override
    public Bookmark findByName(BookmarkName name) {
        JpaBookmark entity = delegate.findAnyByName(name.getValue());
        return Assembler.fromEntity(entity);
    }

    @Override
    public Bookmark findByUrl(BookmarkURL url) {
        JpaBookmark entity = delegate.findAnyByUrl(url.getValue());
        return Assembler.fromEntity(entity);
    }
    
    static class Assembler {
        
        static JpaBookmark toEntity(Bookmark bookmark) {
            if (null == bookmark) {
                return null;
            }
            
            JpaBookmark entity = new JpaBookmark();
            entity.setId(bookmark.getId().getValue());
            entity.setName(bookmark.getName().getValue());
            entity.setDescription(bookmark.getDescription().getValue());
            entity.setUrl(bookmark.getURL().getValue());
            return entity;
        }
        
        static Bookmark fromEntity(JpaBookmark entity) {
            if (null == entity) {
                return null;
            }
            
            return new Bookmark(
                    new BookmarkId(entity.getId()), 
                    new BookmarkName(entity.getName()), 
                    new BookmarkDescription(entity.getDescription()),
                    new BookmarkURL(entity.getDescription()));
        }
    }

}
