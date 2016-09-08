package io.github.bookcrawler.core.impl.helionparser;

import io.github.bookcrawler.cache.AuthorsCache;
import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelionBookParser implements BookInfoParser {

    @Autowired
    AuthorsCache authorsCache;

    @Override
    public BookInfo parse(SourceScrappingResult sourceScrappingResult) {
        if (sourceScrappingResult.isSuccessful()) {
            return new BookInfoBuilder()
                    .title(sourceScrappingResult.parseHelionTitle())
                    .url(sourceScrappingResult.location())
                    .author(authorsCache.getAuthorFromCache(sourceScrappingResult.parseHelionAuthor()))
                    .description(sourceScrappingResult.parseHelionDescription())
                    .library(sourceScrappingResult.parseHelionlibrary())
                    .price(sourceScrappingResult.parseHelionPrice()).build();
        }
        return new BookInfoBuilder().build();
    }
}
