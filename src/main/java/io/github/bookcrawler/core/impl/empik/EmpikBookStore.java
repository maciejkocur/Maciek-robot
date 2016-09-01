package io.github.bookcrawler.core.impl.empik;

import io.github.bookcrawler.core.BookExtractor;
import io.github.bookcrawler.core.BookStore;
import io.github.bookcrawler.core.BooksLinkCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpikBookStore implements BookStore {

    @Autowired
    private EmpikBooksExtractor empikBooksExtractor;

    @Autowired
    private EmpikBookLinksCrawler empikBookLinksCrawler;

    @Override
    public String startUrl() {
        return "http://www.empik.com/ebooki/promocje/";
    }

    @Override
    public String domainUrl() {
        return "http://www.empik.com";
    }

    @Override
    public BookExtractor extractor() {
        return empikBooksExtractor;
    }

    @Override
    public BooksLinkCrawler crawler() {
        return empikBookLinksCrawler;
    }


}
