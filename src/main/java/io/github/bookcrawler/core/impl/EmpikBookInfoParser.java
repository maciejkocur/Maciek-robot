package io.github.bookcrawler.core.impl;

import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.entities.BookInfo;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class EmpikBookInfoParser implements BookInfoParser {

    @Override
    public Collection<BookInfo> parse(Collection<String> urls) {
        return urls.stream()
                .map(url -> new BookInfo(SourceScrapper.scrap(url).getValue()
                        .getElementsByAttributeValue("class", "productMainTitle")
                        .get(0)
                        .text()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
