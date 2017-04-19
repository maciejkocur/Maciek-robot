package io.github.bookcrawler.core.impl.helionparser;

import io.github.bookcrawler.core.BookExtractor;
import io.github.bookcrawler.core.BookStore;
import io.github.bookcrawler.core.BooksLinkCrawler;
import io.github.bookcrawler.core.impl.empik.EmpikBooksExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelionBookStore implements BookStore {
    @Autowired
    private HelionBookExtractor helionBooksExtractor;

    @Override
    public String startUrl() {
        return "http://helion.pl/promocja-dnia";
    }

    @Override
    public String domainUrl() {
        return "http://helion.pl";
    }

    @Override
    public BookExtractor extractor() {
        return helionBooksExtractor;
    }

    @Override
    public BooksLinkCrawler crawler() {
        //empty, not used
        return null;
    }
}
