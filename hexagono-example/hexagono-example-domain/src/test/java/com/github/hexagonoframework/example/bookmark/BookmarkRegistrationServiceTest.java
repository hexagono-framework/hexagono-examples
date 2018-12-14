package com.github.hexagonoframework.example.bookmark;

import static com.github.hexagonoframework.example.bookmark.BookmarkMaker.Bookmark;
import static com.github.hexagonoframework.example.bookmark.BookmarkRepositoryMaker.FakeBookmarkRepository;
import static com.github.hexagonoframework.example.bookmark.BookmarkRepositoryMaker.bookmarks;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.github.hexagonoframework.example.bookmark.Bookmark;
import com.github.hexagonoframework.example.bookmark.BookmarkDescription;
import com.github.hexagonoframework.example.bookmark.BookmarkName;
import com.github.hexagonoframework.example.bookmark.BookmarkNameAlreadyExistsException;
import com.github.hexagonoframework.example.bookmark.BookmarkRegistrationService;
import com.github.hexagonoframework.example.bookmark.BookmarkRepository;
import com.github.hexagonoframework.example.bookmark.BookmarkURL;
import com.github.hexagonoframework.example.bookmark.BookmarkURLAlreadyExistsException;

public class BookmarkRegistrationServiceTest {

    BookmarkRepository repository;
    BookmarkRegistrationService service;
    BookmarkName name = new BookmarkName("Name");
    BookmarkDescription description = new BookmarkDescription("Description");
    BookmarkURL url = new BookmarkURL("http://test.url");
    Throwable throwable;

    @Test
    public void registrationOK() {
        // given
        repository = make(a(FakeBookmarkRepository));

        // when
        service = new BookmarkRegistrationService(repository);
        Bookmark bookmark = service.register(name, description, url);

        // then
        assertNotNull(bookmark);
        assertNotNull(bookmark.getId());
        assertEquals(name, bookmark.getName());
        assertEquals(description, bookmark.getDescription());
        assertEquals(url, bookmark.getURL());
    }

    @Test
    public void registrationWithNameAlreadyExisting() {
        // given
        Bookmark bookmark = make(a(Bookmark));
        repository = make(a(FakeBookmarkRepository, with(bookmarks, asList(bookmark))));

        // when
        service = new BookmarkRegistrationService(repository);
        throwable = catchThrowable(() -> service.register(bookmark.getName(), description, url));

        // then
        assertThat(throwable).isInstanceOf(BookmarkNameAlreadyExistsException.class);
        BookmarkNameAlreadyExistsException exception = (BookmarkNameAlreadyExistsException) throwable;
        assertEquals(bookmark.getName(), exception.getAlreadyExistingName());
    }
    
    @Test
    public void registrationWithURLAlreadyExisting() {
        // given
        Bookmark bookmark = make(a(Bookmark));
        repository = make(a(FakeBookmarkRepository, with(bookmarks, asList(bookmark))));

        // when
        service = new BookmarkRegistrationService(repository);
        Throwable throwable = catchThrowable(() -> service.register(name, description, bookmark.getURL()));

        // then
        assertThat(throwable).isInstanceOf(BookmarkURLAlreadyExistsException.class);
        BookmarkURLAlreadyExistsException exception = (BookmarkURLAlreadyExistsException) throwable;
        assertEquals(bookmark.getURL(), exception.getAlreadyExistingURL());
    }

}
