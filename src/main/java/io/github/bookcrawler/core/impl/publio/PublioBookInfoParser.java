package io.github.bookcrawler.core.impl.publio;

import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import static io.github.bookcrawler.core.BookStore.PUBLIO;

public class PublioBookInfoParser implements BookInfoParser {

    @Override
    public BookInfo parse(SourceScrappingResult sourceScrappingResult) {
        Document source = sourceScrappingResult.getSource();
        return new BookInfoBuilder()
                .title(parseTitle(source))
                .author(parseAuthor(source))
                .description(parseDescription(source))
                .price(parsePrice(source))
                .library(PUBLIO.toString())
                .url(source.location())
                .build();
    }

    private String parseTitle(Element source) {
        return getTextByAttribute(source, "class", "product-card-title");
    }

    private String parseAuthor(Element source) {
        return source.getElementsByAttributeValue("class", "product-card-infos")
                .get(0)
                .child(0)
                .child(1)
                .text();
    }

    private String parseDescription(Element source) {
        return getTextByAttribute(source, "id", "product-card-publication-description");
    }

    private String parsePrice(Element source) {
        return getTextByAttribute(source, "class", "product-card-price-promotion");
    }

    private String getTextByAttribute(Element source, String attr, String value) {
        return source
                .getElementsByAttributeValue(attr, value)
                .get(0)
                .text();
    }
}
