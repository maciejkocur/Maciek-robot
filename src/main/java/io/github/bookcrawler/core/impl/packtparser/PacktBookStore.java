package io.github.bookcrawler.core.impl.packtparser;

import io.github.bookcrawler.core.BookExtractor;
import io.github.bookcrawler.core.BookStore;
import io.github.bookcrawler.core.BooksLinkCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacktBookStore implements BookStore {

    @Autowired
    private PacktBookExtractor packtBooksExtractor;

    @Autowired
    private PacktBooksCrawler packtBookLinksCrawler;

    @Override
    public String startUrl() {
        return "https://www.packtpub.com/packt/offers/free-learning/";
    }

    @Override
    public String domainUrl() {
        return "https://www.packtpub.com";
    }

    @Override
    public BookExtractor extractor() {
        return packtBooksExtractor;
    }

    @Override
    public BooksLinkCrawler crawler() {
        return packtBookLinksCrawler;
    }


}
