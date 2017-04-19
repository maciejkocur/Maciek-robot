package io.github.bookcrawler.core.impl.publio;

import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;

import static io.github.bookcrawler.core.BookStore.PUBLIO;

public class PublioBookInfoParser implements BookInfoParser {

    @Override
    public BookInfo parse(SourceScrappingResult sourceScrappingResult) {
        return new BookInfoBuilder()
                .title(sourceScrappingResult.parsePublioTitle())
                .author(sourceScrappingResult.parsePublioAuthor())
                .description(sourceScrappingResult.parsePublioDescription())
                .price(sourceScrappingResult.parsePublioPrice())
                .library(PUBLIO.toString())
                .url(sourceScrappingResult.location())
                .build();
    }


}
