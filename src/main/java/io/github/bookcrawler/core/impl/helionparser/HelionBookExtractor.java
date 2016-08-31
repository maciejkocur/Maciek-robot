package io.github.bookcrawler.core.impl.helionparser;

import io.github.bookcrawler.core.BookExtractor;
import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.entities.BookInfo;

import java.util.Collections;
import java.util.List;

import static io.github.bookcrawler.core.BookStore.HELION;

public class HelionBookExtractor implements BookExtractor {

    private SourceScrapper sourceScrapper;

    public HelionBookExtractor(SourceScrapper sourceScrapper) {
        this.sourceScrapper = sourceScrapper;
    }

    @Override
    public List<BookInfo> extract() {

        return Collections.singletonList(
                HELION.parser().parse(
                        sourceScrapper.scrap(
                                HELION.startUrl())));
    }
}
