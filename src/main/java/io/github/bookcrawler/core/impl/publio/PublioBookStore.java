package io.github.bookcrawler.core.impl.publio;

import io.github.bookcrawler.core.BookExtractor;
import io.github.bookcrawler.core.BookStore;
import io.github.bookcrawler.core.BooksLinkCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublioBookStore implements BookStore {

    @Autowired
    private BookExtractor publioBooksExtractor;

    @Autowired
    private BooksLinkCrawler publioBookLinksCrawler;

    @Override
    public String startUrl() {
        return "http://www.publio.pl/szukaj,promocja.html?sections=EBOOK";
    }

    @Override
    public String domainUrl() {
        return "http://www.publio.pl";
    }

    @Override
    public BookExtractor extractor() {
        return publioBooksExtractor;
    }

    @Override
    public BooksLinkCrawler crawler() {
        return publioBookLinksCrawler;
    }
}