package io.github.bookcrawler.core.impl;

import io.github.bookcrawler.core.BookExtractor;
import io.github.bookcrawler.entities.BookInfo;

import java.util.Collection;

import static io.github.bookcrawler.core.BookStore.EMPIK;

public class EmpikBooksExtractor implements BookExtractor {

    public Collection<BookInfo> extract() {
        return EMPIK.parser().parse(EMPIK.crawler().crawl());
    }
}

