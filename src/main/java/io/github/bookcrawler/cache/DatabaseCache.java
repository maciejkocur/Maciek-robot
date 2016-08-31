package io.github.bookcrawler.cache;

import io.github.bookcrawler.entities.BookInfo;

import java.util.Collection;
import java.util.List;

public interface DatabaseCache {
    void putAllBookInfos(Collection<BookInfo> allBooks);
    List<BookInfo> getAllBooksInfo();
}
