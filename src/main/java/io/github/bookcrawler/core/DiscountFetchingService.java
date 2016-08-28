package io.github.bookcrawler.core;

import io.github.bookcrawler.entities.BookInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class DiscountFetchingService {
    public Collection<BookInfo> fetch(Collection<BookStore> bookStores) {

        return bookStores.parallelStream()
                .flatMap(b -> b.extractor().extract().parallelStream())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        final long start = System.currentTimeMillis();
        Collection<BookStore> booksStores = new ArrayList<>();
        booksStores.add(BookStore.EMPIK);
        Collection<BookInfo> books = new DiscountFetchingService().fetch(booksStores);
        books.forEach(System.out::println);
        System.out.println(books.size());
        System.out.println(System.currentTimeMillis() - start);
    }
}
