package io.github.bookcrawler.core;

import io.github.bookcrawler.entities.BookInfo;

import java.util.ArrayList;
import java.util.Collection;

public class DiscountFetchingService {
    public Collection<BookInfo> fetch(Collection<BookStore> bookStores) {
        Collection<BookInfo> books = new ArrayList<>();
        for (BookStore bookStore : bookStores) {
            books.addAll(bookStore.extractor().extract());
        }
        return books;
    }

    public static void main(String[] args) {
        Collection<BookStore> booksStores = new ArrayList<>();
        booksStores.add(BookStore.EMPIK);
        Collection<BookInfo> books = new DiscountFetchingService().fetch(booksStores);
        books.forEach(System.out::println);
        System.out.println(books.size());
    }
}
