package com.github.hexagonoframework.example.bookmark.query;

import com.github.hexagonoframework.example.bookmark.BookmarkId;

public interface BookmarkQueryExecutor {

    BookmarkDto retrieve(BookmarkId id);

}
