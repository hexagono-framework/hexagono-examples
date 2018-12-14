package com.github.hexagonoframework.example.bookmark;

import static com.github.hexagonoframework.example.bookmark.BookmarkMaker.Bookmark;
import static com.github.hexagonoframework.example.bookmark.BookmarkRepositoryMaker.FakeBookmarkRepository;
import static com.github.hexagonoframework.example.bookmark.BookmarkRepositoryMaker.bookmarks;
import static com.github.hexagonoframework.example.bookmark.UpdateBookmark.ErrorCode.BOOKMARK_DOES_NOT_EXIST;
import static com.github.hexagonoframework.example.bookmark.UpdateBookmark.ErrorCode.BOOKMARK_NAME_ALREADY_EXISTS;
import static com.github.hexagonoframework.example.bookmark.UpdateBookmark.ErrorCode.BOOKMARK_URL_ALREADY_EXISTS;
import static com.github.hexagonoframework.example.bookmark.UpdateBookmark.ErrorCode.INVALID_BOOKMARK_DESCRIPTION;
import static com.github.hexagonoframework.example.bookmark.UpdateBookmark.ErrorCode.INVALID_BOOKMARK_ID;
import static com.github.hexagonoframework.example.bookmark.UpdateBookmark.ErrorCode.INVALID_BOOKMARK_NAME;
import static com.github.hexagonoframework.example.bookmark.UpdateBookmark.ErrorCode.INVALID_BOOKMARK_URL;
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
import com.github.hexagonoframework.example.bookmark.BookmarkName;
import com.github.hexagonoframework.example.bookmark.BookmarkRepository;
import com.github.hexagonoframework.example.bookmark.BookmarkURL;
import com.github.hexagonoframework.example.bookmark.UpdateBookmark;
import com.github.hexagonoframework.example.bookmark.UpdateBookmark.UpdateBookmarkException;

public class UpdateBookmarkTest {

    BookmarkRepository repository;
    UpdateBookmark command;
    BookmarkData data;

    Bookmark bookmark;
    String name;
    String description;
    String url;

    @Before
    public void before() {
        bookmark = make(a(Bookmark));
        repository = make(a(FakeBookmarkRepository, with(bookmarks, asList(bookmark))));
        command = new UpdateBookmark(repository);
        
        name = "Bookmark Name Changed";
        description = "Bookmark Description Changed";
        url = "http://changed.bookmark.url";
    }

    @Test
    public void modificationWithNullId() {
        // given
        BookmarkId bookmarkId = null;
        data = new BookmarkData(name, description, url);

        // when
        UpdateBookmarkException exception = (UpdateBookmarkException) catchThrowable(() -> command.execute(bookmarkId, data));

        // then
        assertNotNull(exception);
        assertEquals(INVALID_BOOKMARK_ID, exception.getErrorCode());
    }
    
    @Test
    public void modificationWithNullName() {
        // given
        name = null;
        data = new BookmarkData(name, description, url);

        // when
        UpdateBookmarkException exception = (UpdateBookmarkException) catchThrowable(() -> command.execute(bookmark.getId(), data));

        // then
        assertNotNull(exception);
        assertEquals(INVALID_BOOKMARK_NAME, exception.getErrorCode());
    }

    @Test
    public void modificationWithNullDescription() {
        // given
        description = null;
        data = new BookmarkData(name, description, url);

        // when
        UpdateBookmarkException exception = (UpdateBookmarkException) catchThrowable(() -> command.execute(bookmark.getId(), data));

        // then
        assertNotNull(exception);
        assertEquals(INVALID_BOOKMARK_DESCRIPTION, exception.getErrorCode());
    }

    @Test
    public void modificationWithNullURL() {
        // given
        url = null;
        data = new BookmarkData(name, description, url);

        // when
        UpdateBookmarkException exception = (UpdateBookmarkException) catchThrowable(() -> command.execute(bookmark.getId(), data));

        // then
        assertNotNull(exception);
        assertEquals(INVALID_BOOKMARK_URL, exception.getErrorCode());
    }

    @Test
    public void modificationWithNameAlreadyExisting() {
        // given
        Bookmark original = make(a(Bookmark));
        Bookmark other = make(a(Bookmark, with(BookmarkMaker.name, new BookmarkName(name))));
        repository = make(a(FakeBookmarkRepository, with(bookmarks, asList(original, other))));
        command = new UpdateBookmark(repository);
        data = new BookmarkData(name, description, url);

        // when
        Throwable throwable = catchThrowable(() -> command.execute(original.getId(), data));

        // then
        assertThat(throwable).isInstanceOf(UpdateBookmarkException.class);
        UpdateBookmarkException exception = (UpdateBookmarkException) throwable;
        assertEquals(BOOKMARK_NAME_ALREADY_EXISTS, exception.getErrorCode());
    }

    @Test
    public void modificationWithURLAlreadyExisting() {
        // given
        Bookmark original = make(a(Bookmark));
        Bookmark other = make(a(Bookmark, with(BookmarkMaker.url, new BookmarkURL(url))));
        repository = make(a(FakeBookmarkRepository, with(bookmarks, asList(original, other))));
        command = new UpdateBookmark(repository);
        data = new BookmarkData(name, description, url);

        // when
        Throwable throwable = catchThrowable(() -> command.execute(original.getId(), data));

        // then
        assertThat(throwable).isInstanceOf(UpdateBookmarkException.class);
        UpdateBookmarkException exception = (UpdateBookmarkException) throwable;
        assertEquals(BOOKMARK_URL_ALREADY_EXISTS, exception.getErrorCode());
    }
    
    @Test
    public void modificationOfNonExistingBookmark() {
        // given
        data = new BookmarkData(name, description, url);

        // when
        Throwable throwable = catchThrowable(() -> command.execute(BookmarkId.generate(), data));

        // then
        assertThat(throwable).isInstanceOf(UpdateBookmarkException.class);
        UpdateBookmarkException exception = (UpdateBookmarkException) throwable;
        assertEquals(BOOKMARK_DOES_NOT_EXIST, exception.getErrorCode());
    }

    @Test
    public void modificationOK() {
        // given
        data = new BookmarkData(name, description, url);
        
        // when
        command.execute(bookmark.getId(), data);

        // then
        
    }

}
