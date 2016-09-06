package io.github.bookcrawler.core.impl.empik;

import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;

import static io.github.bookcrawler.core.BookStore.EMPIK;

public class EmpikBookInfoParser implements BookInfoParser {

    @Override
    public BookInfo parse(SourceScrappingResult sourceScrappingResult) {
        return new BookInfoBuilder()
                .title(sourceScrappingResult.parseEmpikTitle())
                .author(sourceScrappingResult.parseEmpikAuthor())
                .description(sourceScrappingResult.parseEmpikDescription())
                .price(sourceScrappingResult.parseEmpikPrice())
                .library(EMPIK.toString())
                .url(EMPIK.domainUrl())
                .build();
    }
}
