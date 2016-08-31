package io.github.bookcrawler.core.impl.publio;

import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;

public class PublioBookInfoParser implements BookInfoParser {
    @Override
    public BookInfo parse(SourceScrappingResult sourceScrappingResult) {
        return new BookInfoBuilder().author("A").title("B").description("C").library("D").price("E").url("F").build();
    }
}
