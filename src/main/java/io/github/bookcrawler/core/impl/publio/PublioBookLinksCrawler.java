package io.github.bookcrawler.core.impl.publio;

import io.github.bookcrawler.core.BooksLinkCrawler;
import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.bookcrawler.core.BookStore.PUBLIO;

public class PublioBookLinksCrawler implements BooksLinkCrawler {
    @Override
    public List<String> crawl(String url, SourceScrapper sourceScrapper) {

        SourceScrappingResult sourceScrappingResult = sourceScrapper.scrap(url);
        if (!sourceScrappingResult.isSuccessful()) {
            return Collections.emptyList();
        }

        Integer pages = getPageQuantity(sourceScrappingResult);
        List<String> urls = new ArrayList<>();

        for (int i = 1; i < pages; i++) {
            urls.addAll(getBookElements(sourceScrappingResult.getSource())
                    .parallelStream()
                    .map(e -> getLink(href(e)))
                    .collect(Collectors.toList()));

            sourceScrappingResult = sourceScrapper.scrap(getNextUrl(sourceScrappingResult));
            if (!sourceScrappingResult.isSuccessful()) {
                return Collections.emptyList();
            }
        }
        return urls;
    }

    private String getNextUrl(SourceScrappingResult sourceScrappingResult) {
        return getLink(sourceScrappingResult
                .getSource()
                .getElementsByAttributeValue("class", "pages-list")
                .get(0)
                .getElementsByAttributeValue("class", "page page--next")
                .attr("href"));
    }

    private Integer getPageQuantity(SourceScrappingResult sourceScrappingResult) {
        return Integer.valueOf(sourceScrappingResult.getSource().getElementsByAttributeValue("class", "pages-list").get(0).children().get(6).text());
    }

    private Elements getBookElements(Document source) {
        return source.getElementsByAttributeValue("class", "product-tile-title");
    }

    private String getLink(String i) {
        return PUBLIO.domainUrl() + i;
    }

    private String href(Element e) {
        return e.getAllElements().attr("href");
    }
}

