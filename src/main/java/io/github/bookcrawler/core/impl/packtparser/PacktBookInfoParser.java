package io.github.bookcrawler.core.impl.packtparser;

import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;


public class PacktBookInfoParser implements BookInfoParser {

    @Override
    public BookInfo parse(SourceScrappingResult sourceScrappingResult) {
        return new BookInfoBuilder()
                .title(sourceScrappingResult.parsePacktTitle())
                .url(sourceScrappingResult.location())
                .author(sourceScrappingResult.parsePacktAuthor())
                .description(sourceScrappingResult.parsePacktDescription())
                .library(sourceScrappingResult.parsePactLibrary())
                .price(sourceScrappingResult.parsePactPrice())
                .build();
    }
}
