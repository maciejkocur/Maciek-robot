package io.github.bookcrawler.core.impl.packtparser;

import io.github.bookcrawler.core.BookExtractor;
import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.bookcrawler.core.BookStore.PACKT;

public class PacktBookExtractor implements BookExtractor {

    private SourceScrapper sourceScrapper;

    public PacktBookExtractor(SourceScrapper sourceScrapper) {
        this.sourceScrapper = sourceScrapper;
    }

    @Override
    public List<BookInfo> extract() {
        return PACKT.crawler().crawl(PACKT.startUrl(), sourceScrapper)
                .stream()
                .map(sourceScrapper::scrap)
                .filter(SourceScrappingResult::isSuccessful)
                .map(PACKT.parser()::parse)
                .collect(Collectors.toList());
    }
}
