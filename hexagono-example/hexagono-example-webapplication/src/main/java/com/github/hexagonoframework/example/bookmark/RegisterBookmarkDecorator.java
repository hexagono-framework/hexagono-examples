package com.github.hexagonoframework.example.bookmark;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.github.hexagonoframework.example.bookmark.BookmarkData;
import com.github.hexagonoframework.example.bookmark.BookmarkId;
import com.github.hexagonoframework.example.bookmark.BookmarkRepository;
import com.github.hexagonoframework.example.bookmark.RegisterBookmark;
import com.github.hexagonoframework.example.webapplication.infrastructure.logging.Log;

@Dependent
public class RegisterBookmarkDecorator extends RegisterBookmark {

    @Inject
    public RegisterBookmarkDecorator(BookmarkRepository repository) {
        super(repository);
    }
    
    @Override
    @Transactional
    @Log
    public BookmarkId execute(BookmarkData data) {
        return super.execute(data);
    }
    
}
