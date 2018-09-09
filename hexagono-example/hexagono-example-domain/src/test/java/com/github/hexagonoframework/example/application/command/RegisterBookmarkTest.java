package com.github.hexagonoframework.example.application.command;
import static com.github.hexagonoframework.example.application.command.RegisterBookmarkException.ErrorCode.BOOKMARK_URL_ALREADY_EXISTS;
import static com.github.hexagonoframework.example.application.command.RegisterBookmarkException.ErrorCode.BOOKMARK_NAME_ALREADY_EXISTS;
import static com.github.hexagonoframework.example.application.command.RegisterBookmarkException.ErrorCode.INVALID_BOOKMARK_DESCRIPTION;
import static com.github.hexagonoframework.example.application.command.RegisterBookmarkException.ErrorCode.INVALID_BOOKMARK_NAME;
import static com.github.hexagonoframework.example.application.command.RegisterBookmarkException.ErrorCode.INVALID_BOOKMARK_URL;
import static com.github.hexagonoframework.example.domain.bookmark.BookmarkMaker.Bookmark;
import static com.github.hexagonoframework.example.domain.bookmark.BookmarkRepositoryMaker.FakeBookmarkRepository;
import static com.github.hexagonoframework.example.domain.bookmark.BookmarkRepositoryMaker.bookmarks;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.github.hexagonoframework.example.domain.bookmark.Bookmark;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkRepository;

public class RegisterBookmarkTest {

    BookmarkRepository repository;
    RegisterBookmark command;
    BookmarkRegistrationData data;
    
    
    @Before
    public void before() {
        repository = make(a(FakeBookmarkRepository));
        command = new RegisterBookmark(repository);
        
        data = new BookmarkRegistrationData();
        data.name = "Bookmark Name";
        data.description = "Bookmark Description";
        data.url = "http://bookmark.url";
    }
    
    @Test
    public void registrationWithNullName() {
        // given 
        data.name = null;
        
        // when
        RegisterBookmarkException exception = (RegisterBookmarkException) catchThrowable(() -> command.execute(data));
        
        // then
        assertNotNull(exception);
        assertEquals(INVALID_BOOKMARK_NAME.name(), exception.errorCode());
    }
    
    @Test
    public void registrationWithNullDescription() {
        // given 
        data.description = null;
        
        // when
        RegisterBookmarkException exception = (RegisterBookmarkException) catchThrowable(() -> command.execute(data));
        
        // then
        assertNotNull(exception);
        assertEquals(INVALID_BOOKMARK_DESCRIPTION.name(), exception.errorCode());
    }
    
    @Test
    public void registrationWithNullURL() {
        // given 
        data.url = null;
        
        // when
        RegisterBookmarkException exception = (RegisterBookmarkException) catchThrowable(() -> command.execute(data));
        
        // then
        assertNotNull(exception);
        assertEquals(INVALID_BOOKMARK_URL.name(), exception.errorCode());
    }
    
    @Test
    public void registrationWithNameAlreadyExisting() {
        // given
        Bookmark bookmark = make(a(Bookmark));
        repository = make(a(FakeBookmarkRepository, with(bookmarks, asList(bookmark))));
        command = new RegisterBookmark(repository);
        data.name = bookmark.getName().getValue();
        
        // when
        Throwable throwable = catchThrowable(() -> command.execute(data));
        
        // then
        assertThat(throwable).isInstanceOf(RegisterBookmarkException.class);
        RegisterBookmarkException exception = (RegisterBookmarkException) throwable;
        assertEquals(BOOKMARK_NAME_ALREADY_EXISTS.name(), exception.errorCode());
    }
    
    @Test
    public void registrationWithURLAlreadyExisting() {
        // given
        Bookmark bookmark = make(a(Bookmark));
        repository = make(a(FakeBookmarkRepository, with(bookmarks, asList(bookmark))));
        command = new RegisterBookmark(repository);
        data.url = bookmark.getURL().getValue();
        
        // when
        Throwable throwable = catchThrowable(() -> command.execute(data));
        
        // then
        assertThat(throwable).isInstanceOf(RegisterBookmarkException.class);
        RegisterBookmarkException exception = (RegisterBookmarkException) throwable;
        assertEquals(BOOKMARK_URL_ALREADY_EXISTS.name(), exception.errorCode());
    }
    
    @Test
    public void registrationOK() {
        // given
        repository = make(a(FakeBookmarkRepository));
        command = new RegisterBookmark(repository);
        
        // when
        BookmarkId bookmarkId = command.execute(data);
        
        // then
        assertNotNull(bookmarkId);
        assertNotNull(repository.retrieve(bookmarkId));
    }
    
}
