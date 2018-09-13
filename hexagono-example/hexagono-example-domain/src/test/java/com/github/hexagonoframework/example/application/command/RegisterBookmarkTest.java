package com.github.hexagonoframework.example.application.command;

import static com.github.hexagonoframework.example.application.command.RegisterBookmark.ErrorCode.BOOKMARK_NAME_ALREADY_EXISTS;
import static com.github.hexagonoframework.example.application.command.RegisterBookmark.ErrorCode.BOOKMARK_URL_ALREADY_EXISTS;
import static com.github.hexagonoframework.example.application.command.RegisterBookmark.ErrorCode.INVALID_BOOKMARK_DESCRIPTION;
import static com.github.hexagonoframework.example.application.command.RegisterBookmark.ErrorCode.INVALID_BOOKMARK_NAME;
import static com.github.hexagonoframework.example.application.command.RegisterBookmark.ErrorCode.INVALID_BOOKMARK_URL;
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

import com.github.hexagonoframework.example.application.command.RegisterBookmark.RegisterBookmarkException;
import com.github.hexagonoframework.example.application.command.RegisterBookmark.RegistrationData;
import com.github.hexagonoframework.example.domain.bookmark.Bookmark;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkId;
import com.github.hexagonoframework.example.domain.bookmark.BookmarkRepository;

public class RegisterBookmarkTest {

    BookmarkRepository repository;
    RegisterBookmark command;
    RegistrationData data;

    String name;
    String description;
    String url;

    @Before
    public void before() {
        repository = make(a(FakeBookmarkRepository));
        command = new RegisterBookmark(repository);
        name = "Bookmark Name";
        description = "Bookmark Description";
        url = "http://bookmark.url";
    }

    @Test
    public void registrationWithNullName() {
        // given
        name = null;
        data = new RegistrationData(name, description, url);

        // when
        RegisterBookmarkException exception = (RegisterBookmarkException) catchThrowable(() -> command.execute(data));

        // then
        assertNotNull(exception);
        assertEquals(INVALID_BOOKMARK_NAME, exception.getErrorCode());
    }

    @Test
    public void registrationWithNullDescription() {
        // given
        description = null;
        data = new RegistrationData(name, description, url);

        // when
        RegisterBookmarkException exception = (RegisterBookmarkException) catchThrowable(() -> command.execute(data));

        // then
        assertNotNull(exception);
        assertEquals(INVALID_BOOKMARK_DESCRIPTION, exception.getErrorCode());
    }

    @Test
    public void registrationWithNullURL() {
        // given
        url = null;
        data = new RegistrationData(name, description, url);

        // when
        RegisterBookmarkException exception = (RegisterBookmarkException) catchThrowable(() -> command.execute(data));

        // then
        assertNotNull(exception);
        assertEquals(INVALID_BOOKMARK_URL, exception.getErrorCode());
    }

    @Test
    public void registrationWithNameAlreadyExisting() {
        // given
        Bookmark bookmark = make(a(Bookmark));
        repository = make(a(FakeBookmarkRepository, with(bookmarks, asList(bookmark))));
        command = new RegisterBookmark(repository);
        name = bookmark.getName().getValue();
        data = new RegistrationData(name, description, url);

        // when
        Throwable throwable = catchThrowable(() -> command.execute(data));

        // then
        assertThat(throwable).isInstanceOf(RegisterBookmarkException.class);
        RegisterBookmarkException exception = (RegisterBookmarkException) throwable;
        assertEquals(BOOKMARK_NAME_ALREADY_EXISTS, exception.getErrorCode());
    }

    @Test
    public void registrationWithURLAlreadyExisting() {
        // given
        Bookmark bookmark = make(a(Bookmark));
        repository = make(a(FakeBookmarkRepository, with(bookmarks, asList(bookmark))));
        command = new RegisterBookmark(repository);
        url = bookmark.getURL().getValue();
        data = new RegistrationData(name, description, url);

        // when
        Throwable throwable = catchThrowable(() -> command.execute(data));

        // then
        assertThat(throwable).isInstanceOf(RegisterBookmarkException.class);
        RegisterBookmarkException exception = (RegisterBookmarkException) throwable;
        assertEquals(BOOKMARK_URL_ALREADY_EXISTS, exception.getErrorCode());
    }

    @Test
    public void registrationOK() {
        // given
        data = new RegistrationData(name, description, url);
        
        // when
        BookmarkId bookmarkId = command.execute(data);

        // then
        assertNotNull(bookmarkId);
        assertNotNull(repository.retrieve(bookmarkId));
    }

}
