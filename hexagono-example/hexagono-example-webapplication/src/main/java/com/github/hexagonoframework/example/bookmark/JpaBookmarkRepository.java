package com.github.hexagonoframework.example.bookmark;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.github.hexagonoframework.example.bookmark.Bookmark;
import com.github.hexagonoframework.example.bookmark.BookmarkDescription;
import com.github.hexagonoframework.example.bookmark.BookmarkId;
import com.github.hexagonoframework.example.bookmark.BookmarkName;
import com.github.hexagonoframework.example.bookmark.BookmarkRepository;
import com.github.hexagonoframework.example.bookmark.BookmarkURL;

@Dependent
public class JpaBookmarkRepository implements BookmarkRepository {

    @Inject
    private JpaBookmarkRepositoryDelegate delegate;
    
    @Override
    public void store(Bookmark bookmark) {
        JpaBookmark entity = Assembler.toEntity(bookmark);
        delegate.saveAndFlush(entity);
    }

    @Override
    public Bookmark retrieve(BookmarkId id) {
        JpaBookmark entity = delegate.findBy(id.value());
        if (null == entity) {
            return null;
        }
        
        return Assembler.fromEntity(entity);
    }

    @Override
    public Bookmark findByName(BookmarkName name) {
        JpaBookmark entity = delegate.findAnyByName(name.value());
        return Assembler.fromEntity(entity);
    }

    @Override
    public Bookmark findByUrl(BookmarkURL url) {
        JpaBookmark entity = delegate.findAnyByUrl(url.value());
        return Assembler.fromEntity(entity);
    }
    
    static class Assembler {
        
        static JpaBookmark toEntity(Bookmark bookmark) {
            if (null == bookmark) {
                return null;
            }
            
            JpaBookmark entity = new JpaBookmark();
            entity.setId(bookmark.getId().value());
            entity.setName(bookmark.getName().value());
            entity.setDescription(bookmark.getDescription().value());
            entity.setUrl(bookmark.getURL().value());
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
