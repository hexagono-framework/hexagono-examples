package com.github.hexagonoframework.example.webapplication.application.command;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.github.hexagonoframework.example.application.command.BookmarkData;
import com.github.hexagonoframework.example.application.command.UpdateBookmark;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkRepository;

@Dependent
@Stateless
public class UpdateBookmarkAdapter extends UpdateBookmark {

    private static final Logger LOGGER = Logger.getLogger(UpdateBookmarkAdapter.class.getSimpleName());
    
    public UpdateBookmarkAdapter() {
        this(null);
    }
    
    @Inject
    public UpdateBookmarkAdapter(BookmarkRepository repository) {
        super(repository);
    }
    
    @Override
    public void execute(BookmarkId id, BookmarkData data) {
        LOGGER.info("Changing bookmark with id " + id.getValue());
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
