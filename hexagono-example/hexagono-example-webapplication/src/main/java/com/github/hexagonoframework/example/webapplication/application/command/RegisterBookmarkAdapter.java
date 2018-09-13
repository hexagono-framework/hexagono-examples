package com.github.hexagonoframework.example.webapplication.application.command;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.github.hexagonoframework.example.application.command.RegisterBookmark;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkRepository;

@Dependent
@Stateless
public class RegisterBookmarkAdapter extends RegisterBookmark {

    private static final Logger LOGGER = Logger.getLogger(RegisterBookmarkAdapter.class.getSimpleName());
    
    public RegisterBookmarkAdapter() {
        this(null);
    }
    
    @Inject
    public RegisterBookmarkAdapter(BookmarkRepository repository) {
        super(repository);
    }
    
    @Override
    public BookmarkId execute(RegistrationData data) {
        LOGGER.info("Registering bookmark with name " + data.name);
        try {
            return super.execute(data);
        } catch (RegisterBookmarkException e) {
            LOGGER.log(INFO, e.getMessage());
            throw e;
        } catch (RuntimeException e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

}
