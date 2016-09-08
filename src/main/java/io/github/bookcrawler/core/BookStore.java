package io.github.bookcrawler.core;

public interface BookStore {
    String startUrl();

    String domainUrl();

    BookExtractor extractor();

    BooksLinkCrawler crawler();
}


