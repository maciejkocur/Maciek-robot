package io.github.bookcrawler.core.impl.packtparser;

import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class PacktBookInfoParser implements BookInfoParser {


    private static final String LIBRARY_NAME = "Packt";
    private static final String PRICE = "free";
    private static final String DESCRIPTION_CLASS_NAME = "book-top-block-info-one-liner";
    private static final String AUTHOR_CLASS_NAME = "book-top-block-info-authors";
    private static final String TITLE_CLASS_NAME = "book-top-block-info-title";

    @Override
    public BookInfo parse(SourceScrappingResult sourceScrappingResult) {
        Document document = sourceScrappingResult.getSource();
        return new BookInfoBuilder()
                .title(getTitle(document))
                .url(getLocation(document))
                .author(getAuthor(document))
                .description(getDescription(document))
                .library(LIBRARY_NAME)
                .price(PRICE).build();
    }

    private String getDescription(Document document) {
        return getClassElements(document, DESCRIPTION_CLASS_NAME).get(0).ownText();
    }

    private Elements getClassElements(Document document, String className) {
        return document.getElementsByClass(className);
    }

    private String getAuthor(Document document) {
        return getClassElements(document, AUTHOR_CLASS_NAME).get(0).ownText();
    }

    private String getLocation(Document document) {
        return document.location();
    }

    private String getTitle(Document document) {
        return getClassElements(document, TITLE_CLASS_NAME).select("h1").text();
    }

}
