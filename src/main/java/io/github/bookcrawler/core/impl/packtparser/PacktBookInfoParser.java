package io.github.bookcrawler.core.impl.packtparser;

import io.github.bookcrawler.cache.AuthorsCache;
import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacktBookInfoParser implements BookInfoParser {

    @Autowired
    AuthorsCache authorsCache;

    @Override
    public BookInfo parse(SourceScrappingResult sourceScrappingResult) {
        return new BookInfoBuilder()
                .title(sourceScrappingResult.parsePacktTitle())
                .url(sourceScrappingResult.location())
                .author(authorsCache.getAuthorFromCache(sourceScrappingResult.parsePacktAuthor().replaceAll("[^A-Za-z\\p{L}]", " ").trim()))
                .description(sourceScrappingResult.parsePacktDescription())
                .library(sourceScrappingResult.parsePactLibrary())
                .price(sourceScrappingResult.parsePactPrice())
                .build();
    }
}
