package com.github.hexagonoframework.example.bookmark;

import static com.github.hexagonoframework.example.bookmark.BookmarkMaker.Bookmark;
import static com.github.hexagonoframework.example.bookmark.BookmarkRepositoryMaker.FakeBookmarkRepository;
import static com.github.hexagonoframework.example.bookmark.BookmarkRepositoryMaker.bookmarks;
import static com.github.hexagonoframework.example.bookmark.RegisterBookmark.ErrorCode.BOOKMARK_NAME_ALREADY_EXISTS;
import static com.github.hexagonoframework.example.bookmark.RegisterBookmark.ErrorCode.BOOKMARK_URL_ALREADY_EXISTS;
import static com.github.hexagonoframework.example.bookmark.RegisterBookmark.ErrorCode.INVALID_BOOKMARK_DESCRIPTION;
import static com.github.hexagonoframework.example.bookmark.RegisterBookmark.ErrorCode.INVALID_BOOKMARK_NAME;
import static com.github.hexagonoframework.example.bookmark.RegisterBookmark.ErrorCode.INVALID_BOOKMARK_URL;
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

import com.github.hexagonoframework.example.bookmark.Bookmark;
import com.github.hexagonoframework.example.bookmark.BookmarkData;
import com.github.hexagonoframework.example.bookmark.BookmarkId;
import com.github.hexagonoframework.example.bookmark.BookmarkRepository;
import com.github.hexagonoframework.example.bookmark.RegisterBookmark;
import com.github.hexagonoframework.example.bookmark.RegisterBookmark.RegisterBookmarkException;

public class RegisterBookmarkTest {

    BookmarkRepository repository;
    RegisterBookmark command;
    BookmarkData data;

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
        data = new BookmarkData(name, description, url);

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
        data = new BookmarkData(name, description, url);

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
        data = new BookmarkData(name, description, url);

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
        name = bookmark.getName().value();
        data = new BookmarkData(name, description, url);

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
        url = bookmark.getURL().value();
        data = new BookmarkData(name, description, url);

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
        data = new BookmarkData(name, description, url);
        
        // when
        BookmarkId bookmarkId = command.execute(data);

        // then
        assertNotNull(bookmarkId);
        assertNotNull(repository.retrieve(bookmarkId));
    }

}
