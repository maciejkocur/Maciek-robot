package io.github.bookcrawler.core.impl.helionparser;

import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;

public class HelionBookParser implements BookInfoParser {

    @Override
    public BookInfo parse(SourceScrappingResult sourceScrappingResult) {
        if (sourceScrappingResult.isSuccessful()) {
            return new BookInfoBuilder()
                    .title(sourceScrappingResult.parseHelionTitle())
                    .url(sourceScrappingResult.location())
                    .author(sourceScrappingResult.parseHelionAuthor())
                    .description(sourceScrappingResult.parseHelionDescription())
                    .library(sourceScrappingResult.parseHelionlibrary())
                    .price(sourceScrappingResult.parseHelionPrice()).build();
        }
        return new BookInfoBuilder().build();
    }
}
