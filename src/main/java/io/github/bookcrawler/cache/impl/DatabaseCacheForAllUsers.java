package io.github.bookcrawler.cache.impl;

import io.github.bookcrawler.cache.DatabaseCache;
import io.github.bookcrawler.entities.BookInfo;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DatabaseCacheForAllUsers implements DatabaseCache {

    Collection<BookInfo> allBooks;

    @Override
    public void putAllBookInfos(Collection<BookInfo> allBooks) {
        this.allBooks = allBooks;
    }

    @Override
    public List<BookInfo> getAllBooksInfo() {
        return allBooks.isEmpty() ? Collections.EMPTY_LIST : allBooks.stream().collect(Collectors.toList());
    }
}
