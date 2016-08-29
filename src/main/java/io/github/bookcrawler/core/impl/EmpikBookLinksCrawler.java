package io.github.bookcrawler.core.impl;

import io.github.bookcrawler.core.BooksLinkCrawler;
import io.github.bookcrawler.core.SourceScrapper;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import static io.github.bookcrawler.core.BookStore.EMPIK;

public class EmpikBookLinksCrawler implements BooksLinkCrawler {
    private final SourceScrapper sourceScrapper;

    public EmpikBookLinksCrawler(SourceScrapper sourceScrapper) {
        this.sourceScrapper = sourceScrapper;
    }

    @Override
    public Collection<String> crawl() {

        return getSectionElements().parallelStream()
                .flatMap(e -> getBookElements(e).parallelStream())
                .filter(e -> !href(e).isEmpty())
                .map(e -> getLink(href(e)))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private Elements getBookElements(Element e) {
        return sourceScrapper.scrap(getLink(href(e)))
                .getSource()
                .getElementsByAttributeValue("class", "productBox-450Pic");
    }

    private Elements getSectionElements() {
        return sourceScrapper.scrap(EMPIK.startUrl())
                .getSource()
                .getElementsByAttributeValue("class", "more_lnk");
    }

    private String getLink(String i) {
        return EMPIK.domainUrl() + i;
    }

    private String href(Element e) {
        return e.attr("href");
    }
}
