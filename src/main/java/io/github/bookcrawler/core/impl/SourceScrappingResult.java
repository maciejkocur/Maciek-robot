package io.github.bookcrawler.core.impl;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SourceScrappingResult {
    private final Document source;
    private final SourceScrappingStatus status;

    private static final String PUBLIO_TITLE_CLASS_NAME = "product-card-title";
    private static final String PUBLIO_AUTHOR_CLASS_NAME = "product-card-infos";
    private static final String PUBLIO_PRICE_CLASS_NAME = "product-card-price-promotion";
    private static final String PUBLIO_DESCRIPTION_CLASS_NAME = "product-card-publication-description";
    
    private static final String EMPIK_TITLE_CLASS_NAME = "productMainTitle";
    private static final String EMPIK_AUTHOR_CLASS_NAME = "pDAuthorList";
    private static final String EMPIK_DESCRIPTION_CLASS_NAME = "contentPacketText longDescription";
    public static final String EMPIK_PRICE_CLASS_NAME = "currentPrice";

    private static final String HELION_TITLE_CLASS_NAME = "title-group";
    private static final String HELION_PRICE = "30% discount";
    private static final String HELION_LIBRARY_CLASS_NAME = "info";
    private static final String HELION_DESCRIPTION_CLASS_NAME = "text";

    private static final String PACKT_LIBRARY_NAME = "Packt";
    private static final String PACKT_PRICE = "free";
    private static final String PACKT_DESCRIPTION_CLASS_NAME = "book-top-block-info-one-liner";
    private static final String PACKT_AUTHOR_CLASS_NAME = "book-top-block-info-authors";
    private static final String PACKT_TITLE_CLASS_NAME = "book-top-block-info-title";

    public SourceScrappingResult(Document source, SourceScrappingStatus status) {
        this.source = source;
        this.status = status;
    }

    public boolean isSuccessful() {
        return status == SourceScrappingStatus.SUCCESS;
    }

    public Document getSource() {
        return source;

    }

    public String location() {
        return source.location();
    }

    public String parsePublioTitle() {
        return getTextByAttribute("class", PUBLIO_TITLE_CLASS_NAME);
    }

    public String parsePublioAuthor() {
        return source.getElementsByAttributeValue("class", PUBLIO_AUTHOR_CLASS_NAME)
                .get(0)
                .child(0)
                .child(1)
                .text();
    }

    public String parsePublioDescription() {
        return getTextByAttribute("id", PUBLIO_DESCRIPTION_CLASS_NAME);
    }

    public String parsePublioPrice() {
        return getTextByAttribute("class", PUBLIO_PRICE_CLASS_NAME);
    }

    public String parseEmpikTitle() {
        return getTextByAttribute("class", EMPIK_TITLE_CLASS_NAME);
    }

    public String parseEmpikAuthor() {
        return getTextByAttribute("class", EMPIK_AUTHOR_CLASS_NAME);
    }

    public String parseEmpikDescription() {
        return getTextByAttribute("class", EMPIK_DESCRIPTION_CLASS_NAME);
    }

    public String parseEmpikPrice() {
        return getTextByAttribute("class", EMPIK_PRICE_CLASS_NAME);
    }

    public String parseHelionlibrary() {
        return getClassElements(HELION_LIBRARY_CLASS_NAME)
                .select("dd[itemprop=publisher]")
                .first()
                .text();
    }

    public String parseHelionDescription() {
        return getClassElements(HELION_DESCRIPTION_CLASS_NAME).select("h4").text();
    }

    public String parseHelionAuthor() {
        return getClassElements(HELION_TITLE_CLASS_NAME).select("span[itemprop=author]").text();
    }

    public String parseHelionTitle() {
        return getClassElements(HELION_TITLE_CLASS_NAME).select("h1").text();
    }

    public String parseHelionPrice() {
        return HELION_PRICE;
    }

    public String parsePacktTitle() {
        return getClassElements(PACKT_TITLE_CLASS_NAME).select("h1").text();
    }

    public String parsePacktAuthor() {
        return getClassElements(PACKT_AUTHOR_CLASS_NAME).get(0).ownText();
    }

    public String parsePacktDescription() {
        return getClassElements(PACKT_DESCRIPTION_CLASS_NAME).get(0).ownText();
    }

    public String parsePactLibrary() {
        return PACKT_LIBRARY_NAME;
    }

    public String parsePactPrice() {
        return PACKT_PRICE;
    }

    private String getTextByAttribute(String attr, String value) {
        return source
                .getElementsByAttributeValue(attr, value)
                .get(0)
                .text();
    }

    private Elements getClassElements(String libraryClassName) {
        return source.getElementsByClass(libraryClassName);
    }
}
