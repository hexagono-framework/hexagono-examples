package com.github.hexagonoframework.example.bookmark;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.github.hexagonoframework.example.bookmark.query.BookmarkDto;
import com.github.hexagonoframework.example.bookmark.query.BookmarkQueryExecutor;

@Dependent
public class JpaBookmarkQueryService implements BookmarkQueryExecutor {
    
    @Inject
    private JpaBookmarkRepositoryDelegate delegate;
    
    @Override
    public BookmarkDto retrieve(BookmarkId id) {
        return Assembler.toDto(delegate.findBy(id.value()));
    }
    
    static class Assembler {
        
        static BookmarkDto toDto(JpaBookmark entity) {
            if (null == entity) {
                return null;
            }
            
            BookmarkDto dto = new BookmarkDto();
            dto.id = entity.getId();
            dto.name = entity.getName();
            dto.description = entity.getDescription();
            dto.url = entity.getUrl();
            return dto;
        }
        
    }
    

    
    
}
