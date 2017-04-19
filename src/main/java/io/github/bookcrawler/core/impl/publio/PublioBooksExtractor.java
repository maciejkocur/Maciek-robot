package io.github.bookcrawler.core.impl.publio;

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
public class PublioBooksExtractor implements BookExtractor {

    @Autowired
    private PublioBookInfoParser publioBookInfoParser;

    @Autowired
    private BookStore publioBookStore;

    @Autowired
    private SourceScrapper jsoupSourceScapper;


    @Override
    public List<BookInfo> extract() {
        return publioBookStore.crawler().crawl(publioBookStore.startUrl(), jsoupSourceScapper).parallelStream()
                .map(url -> jsoupSourceScapper.scrap(url))
                .filter(SourceScrappingResult::isSuccessful)
                .map(sourceScrappingResult -> publioBookInfoParser.parse(sourceScrappingResult))
                .collect(Collectors.toList());
    }
}
