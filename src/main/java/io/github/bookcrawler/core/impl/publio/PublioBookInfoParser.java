package io.github.bookcrawler.core.impl.publio;

import io.github.bookcrawler.cache.AuthorsCache;
import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublioBookInfoParser implements BookInfoParser {

    @Autowired
    AuthorsCache authorsCache;

    @Override
    public BookInfo parse(SourceScrappingResult sourceScrappingResult) {
        return new BookInfoBuilder()
                .title(sourceScrappingResult.parsePublioTitle())
                .author(authorsCache.getAuthorFromCache(sourceScrappingResult.parsePublioAuthor().replaceAll("[^A-Za-z\\p{L}]", " ").trim()))
                .description(sourceScrappingResult.parsePublioDescription())
                .price(sourceScrappingResult.parsePublioPrice())
                .library("PUBLIO")
                .url(sourceScrappingResult.location())
                .build();
    }
}
