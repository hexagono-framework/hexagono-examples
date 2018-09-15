package com.github.hexagonoframework.example.domain.bookmark;

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

import org.junit.Test;

public class BookmarkChangeServiceTest {

    BookmarkRepository repository;
    BookmarkChangeService service;
    BookmarkName name = new BookmarkName("Name Changed");
    BookmarkDescription description = new BookmarkDescription("Description Changed");
    BookmarkURL url = new BookmarkURL("http://changed.test.url");
    Throwable throwable;

    @Test
    public void changeOK() {
        // given
        Bookmark bookmark = make(a(Bookmark));
        repository = make(a(FakeBookmarkRepository, with(bookmarks, asList(bookmark))));
        
        // when
        service = new BookmarkChangeService(repository);
        Bookmark bookmarkUpdated = service.change(bookmark.getId(), name, description, url);

        // then
        assertNotNull(bookmarkUpdated);
        assertNotNull(bookmarkUpdated.getId());
        assertEquals(name, bookmarkUpdated.getName());
        assertEquals(description, bookmarkUpdated.getDescription());
        assertEquals(url, bookmarkUpdated.getURL());
    }

    @Test
    public void changeWithNameAlreadyExisting() {
        // given
        Bookmark original = make(a(Bookmark));
        Bookmark other = make(a(Bookmark, with(BookmarkMaker.name, name)));
        repository = make(a(FakeBookmarkRepository, with(bookmarks, asList(original, other))));

        // when
        service = new BookmarkChangeService(repository);
        throwable = catchThrowable(() -> service.change(original.getId(), name, description, url));

        // then
        assertThat(throwable).isInstanceOf(BookmarkNameAlreadyExistsException.class);
        BookmarkNameAlreadyExistsException exception = (BookmarkNameAlreadyExistsException) throwable;
        assertEquals(name, exception.getAlreadyExistingName());
    }
    
    @Test
    public void changeWithURLAlreadyExisting() {
        // given
        Bookmark original = make(a(Bookmark));
        Bookmark other = make(a(Bookmark, with(BookmarkMaker.url, url)));
        repository = make(a(FakeBookmarkRepository, with(bookmarks, asList(original, other))));

        // when
        service = new BookmarkChangeService(repository);
        throwable = catchThrowable(() -> service.change(original.getId(), name, description, url));

        // then
        assertThat(throwable).isInstanceOf(BookmarkURLAlreadyExistsException.class);
        BookmarkURLAlreadyExistsException exception = (BookmarkURLAlreadyExistsException) throwable;
        assertEquals(url, exception.getAlreadyExistingURL());
    }

}
