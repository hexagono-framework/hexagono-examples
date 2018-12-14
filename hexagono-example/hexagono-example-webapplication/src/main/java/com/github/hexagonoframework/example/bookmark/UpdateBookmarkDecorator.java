package com.github.hexagonoframework.example.bookmark;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.github.hexagonoframework.example.bookmark.BookmarkData;
import com.github.hexagonoframework.example.bookmark.BookmarkId;
import com.github.hexagonoframework.example.bookmark.BookmarkRepository;
import com.github.hexagonoframework.example.bookmark.UpdateBookmark;

@Dependent
public class UpdateBookmarkDecorator extends UpdateBookmark {

    private static final Logger LOGGER = Logger.getLogger(UpdateBookmarkDecorator.class.getSimpleName());
    
    public UpdateBookmarkDecorator() {
        this(null);
    }
    
    @Inject
    public UpdateBookmarkDecorator(BookmarkRepository repository) {
        super(repository);
    }
    
    @Override
    public void execute(BookmarkId id, BookmarkData data) {
        LOGGER.info("Changing bookmark with id " + id.value());
        try {
            super.execute(id, data);
        } catch (UpdateBookmarkException e) {
            LOGGER.log(INFO, e.getMessage());
            throw e;
        } catch (RuntimeException e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
        
        LOGGER.info("Bookmark with name " + data.name + " changed");
    }

}
