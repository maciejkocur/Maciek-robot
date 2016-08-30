package io.github.bookcrawler.core.impl.empik;

import io.github.bookcrawler.core.BookExtractor;
import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.bookcrawler.core.BookStore.EMPIK;

public class EmpikBooksExtractor implements BookExtractor {

    private SourceScrapper sourceScrapper;

    public EmpikBooksExtractor(SourceScrapper sourceScrapper) {
        this.sourceScrapper = sourceScrapper;
    }

    @Override
    public List<BookInfo> extract() {
        return EMPIK.crawler().crawl(EMPIK.startUrl(), sourceScrapper)
                .parallelStream()
                .map(sourceScrapper::scrap)
                .filter(SourceScrappingResult::isSuccessful)
                .map(EMPIK.parser()::parse)
                .collect(Collectors.toList());
    }
}

