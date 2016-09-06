package io.github.bookcrawler.core;

import io.github.bookcrawler.entities.BookInfo;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountFetchingService {
    private List<BookInfo> fetch(EnumSet<BookStore> bookStores) {

        return bookStores.parallelStream()
                .flatMap(b -> b.extractor().extract().parallelStream())
                .collect(Collectors.toList());
    }

    public List<BookInfo> getAllBooks() {
        return fetch(EnumSet.allOf(BookStore.class));
    }

    public List<BookInfo> getEmpikBooks() {
        return fetch(EnumSet.of(BookStore.EMPIK));
    }
}
