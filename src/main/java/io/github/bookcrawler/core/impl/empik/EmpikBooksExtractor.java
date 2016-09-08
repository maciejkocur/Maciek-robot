package io.github.bookcrawler.core.impl.empik;

import io.github.bookcrawler.core.BookExtractor;
import io.github.bookcrawler.core.BookStore;
import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmpikBooksExtractor implements BookExtractor {

    @Autowired
    private EmpikBookInfoParser empikBookInfoParser;

    @Autowired
    private BookStore empikBookStore;

    @Autowired
    private SourceScrapper jsoupSourceScapper;

    @Override
    public List<BookInfo> extract() {
        return empikBookStore.crawler().crawl(empikBookStore.startUrl(), jsoupSourceScapper).parallelStream()
                .map(url -> jsoupSourceScapper.scrap(url))
                .filter(SourceScrappingResult::isSuccessful)
                .map(sourceScrappingResult -> empikBookInfoParser.parse(sourceScrappingResult))
                .collect(Collectors.toList());
    }
}

