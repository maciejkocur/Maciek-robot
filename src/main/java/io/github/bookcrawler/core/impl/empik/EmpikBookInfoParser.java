package io.github.bookcrawler.core.impl.empik;

import io.github.bookcrawler.cache.AuthorsCache;
import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.Author;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class EmpikBookInfoParser implements BookInfoParser {

    @Autowired
    AuthorsCache authorsCache;

    @Override
    public BookInfo parse(SourceScrappingResult sourceScrappingResult) {
        Document source = sourceScrappingResult.getSource();
        return new BookInfoBuilder()
                .title(parseTitle(source))
                .author(parseAuthor(source))
                .description(parseDescription(source))
                .price(parsePrice(source))
                .library("EMPIK")
                .url(source.location())
                .inputDate(Calendar.getInstance().getTime().getTime())
                .build();
    }

    private String parseTitle(Element source) {
        return getTextByAttribute(source, "productMainTitle");
    }

    private Author parseAuthor(Element source) {
        return authorsCache.getAuthorFromCache(getTextByAttribute(source, "pDAuthorList").replaceAll("[^A-Za-z\\p{L}]", " ").trim());
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
