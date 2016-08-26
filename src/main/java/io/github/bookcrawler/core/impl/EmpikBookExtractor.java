package io.github.bookcrawler.core.impl;

import io.github.bookcrawler.core.BookExtractor;
import io.github.bookcrawler.core.BookStore;
import io.github.bookcrawler.entities.BookInfo;

import java.util.Collection;

public class EmpikBookExtractor implements BookExtractor {

    public Collection<BookInfo> extract() {
        Collection<String> bookUrls = new EmpikBooksLinkCrawler().crawl(BookStore.EMPIK);
        return new EmpikBookInfoParser().parse(bookUrls);
    }
}

