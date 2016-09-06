package io.github.bookcrawler.core.impl.empik;

import io.github.bookcrawler.cache.AuthorsCache;
import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpikBookInfoParser implements BookInfoParser {

    @Autowired
    AuthorsCache authorsCache;

    @Override
    public BookInfo parse(SourceScrappingResult sourceScrappingResult) {
        return new BookInfoBuilder()
                .title(sourceScrappingResult.parseEmpikTitle())
                .author(authorsCache.getAuthorFromCache(sourceScrappingResult.parseEmpikAuthor().replaceAll("[^A-Za-z\\p{L}]", " ").trim()))
                .description(sourceScrappingResult.parseEmpikDescription())
                .price(sourceScrappingResult.parseEmpikPrice())
                .library("EMPIK")
                .url(sourceScrappingResult.location())
                .build();
    }
}
