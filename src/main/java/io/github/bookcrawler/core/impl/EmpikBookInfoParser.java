package io.github.bookcrawler.core.impl;

import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.core.BookStore;
import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;
import org.jsoup.nodes.Element;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import static io.github.bookcrawler.core.BookStore.*;

public class EmpikBookInfoParser implements BookInfoParser {
    private final SourceScrapper sourceScrapper;

    public EmpikBookInfoParser(SourceScrapper sourceScrapper) {
        this.sourceScrapper = sourceScrapper;
    }

    //TODO: parsers should parse source, String, jSoup Document or Wrapper
    @Override
    public Collection<BookInfo> parse(Collection<String> urls) {

        return urls.parallelStream()
                .map(url -> createBookInfo(url))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private BookInfo createBookInfo(String url) {
        SourceScrappingResult sourceScrappingResult = sourceScrapper.scrap(url);
        // TODO: null possibility
        Element source = sourceScrappingResult.getSource();
        return new BookInfoBuilder()
                .title(parseTitle(source))
                .author(parseAuthor(source))
                .description(parseDescription(source))
                .price(parsePrice(source))
                .library(EMPIK.toString())
                .url(EMPIK.domainUrl())
                .build();
    }

    private String parseTitle(Element source) {
        return getTextByAttribute(source, "productMainTitle");
    }

    private String parseAuthor(Element source) {
        return getTextByAttribute(source, "pDAuthorList");
    }

    private String parseDescription(Element source) {
        return getTextByAttribute(source, "contentPacketText longDescription");
    }

    private String parsePrice(Element source) {
        return getTextByAttribute(source, "currentPrice");
    }

    private String getTextByAttribute(Element source, String attribute) {
        return source
                .getElementsByAttributeValue("class", attribute)
                .get(0)
                .text();
    }
}
