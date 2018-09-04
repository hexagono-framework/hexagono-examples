package com.github.hexagonoframework.example.domain.bookmark;

import static com.github.hexagonoframework.example.domain.bookmark.BookmarkMaker.Bookmark;
import static com.github.hexagonoframework.example.domain.bookmark.BookmarkRepositoryMaker.BookmarkRepository;
import static com.github.hexagonoframework.example.domain.bookmark.BookmarkRepositoryMaker.bookmarks;
import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class BookmarkRegistrationServiceTest {

    BookmarkRepository repository;
    BookmarkRegistrationService service;
    BookmarkName name = new BookmarkName("Name");
    BookmarkDescription description = new BookmarkDescription("Description");
    BookmarkURL url = new BookmarkURL("http://test.url");

    @Test
    public void registrationOK() {
        // given
        repository = make(a(BookmarkRepository));

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
        repository = make(a(BookmarkRepository, with(bookmarks, asList(bookmark))));

        // when
        service = new BookmarkRegistrationService(repository);
        Throwable throwable = catchThrowable(() -> service.register(bookmark.getName(), description, url));

        // then
        assertThat(throwable).isInstanceOf(BookmarkNameAlreadyExistsException.class);
    }
    
    @Test
    public void registrationWithURLAlreadyExisting() {
        // given
        Bookmark bookmark = make(a(Bookmark));
        repository = make(a(BookmarkRepository, with(bookmarks, asList(bookmark))));

        // when
        service = new BookmarkRegistrationService(repository);
        Throwable throwable = catchThrowable(() -> service.register(name, description, bookmark.getURL()));

        // then
        assertThat(throwable).isInstanceOf(BookmarkURLAlreadyExistsException.class);
    }

}
