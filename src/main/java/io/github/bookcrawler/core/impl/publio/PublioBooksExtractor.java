package io.github.bookcrawler.core.impl.publio;

import io.github.bookcrawler.core.BookExtractor;
import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.bookcrawler.core.BookStore.PUBLIO;

public class PublioBooksExtractor implements BookExtractor {
    private SourceScrapper sourceScrapper;

    public PublioBooksExtractor(SourceScrapper sourceScrapper) {
        this.sourceScrapper = sourceScrapper;
    }

    @Override
    public List<BookInfo> extract() {
        return PUBLIO.crawler().crawl(PUBLIO.startUrl(), sourceScrapper).parallelStream()
                .map(url -> sourceScrapper.scrap(url))
                .filter(SourceScrappingResult::isSuccessful)
                .map(sourceScrappingResult -> PUBLIO.parser().parse(sourceScrappingResult))
                .collect(Collectors.toList());
    }
}
