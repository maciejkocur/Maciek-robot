package io.github.bookcrawler.core.impl.helionparser;

import io.github.bookcrawler.core.BookInfoParser;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.entities.BookInfoBuilder;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HelionBookParser implements BookInfoParser {

    private static final String TITLE_CLASS_NAME = "title-group";
    private static final String PRICE = "30% discount";
    private static final String LIBRARY_CLASS_NAME = "info";
    private static final String DESCRIPTION_CLASS_NAME = "text";

    @Override
    public BookInfo parse(SourceScrappingResult sourceScrappingResult) {
        if (sourceScrappingResult.isSuccessful()) {
        Document document = sourceScrappingResult.getSource();
        return new BookInfoBuilder()
                .title(getTitle(document))
                .url(document.location())
                .author(getAuthor(document))
                .description(getDescription(document))
                .library(getLibrary(document))
                .price(PRICE).build();
        }
        return new BookInfoBuilder().build();
    }

    private String getLibrary(Document document) {
        return getClassElements(document, LIBRARY_CLASS_NAME)
                .select("dd[itemprop=publisher]")
                .first()
                .text();
    }

    private Elements getClassElements(Document document, String libraryClassName) {
        return document.getElementsByClass(libraryClassName);
    }

    private String getDescription(Document document) {
        return getClassElements(document, DESCRIPTION_CLASS_NAME).select("h4").text();
    }

    private String getAuthor(Document document) {
        return getClassElements(document, TITLE_CLASS_NAME).select("span[itemprop=author]").text();
    }

    private String getTitle(Document document) {
        return getClassElements(document, TITLE_CLASS_NAME).select("h1").text();
    }
}
