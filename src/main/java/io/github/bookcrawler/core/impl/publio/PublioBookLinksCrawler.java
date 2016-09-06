package io.github.bookcrawler.core.impl.publio;

import io.github.bookcrawler.core.BooksLinkCrawler;
import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class PublioBookLinksCrawler implements BooksLinkCrawler {


    public static final int ZERO_INDEX = 0;
    public static final int SIX_INDEX = 6;
    
    @Override
    public List<String> crawl(String url, SourceScrapper sourceScrapper) {
        SourceScrappingResult sourceScrappingResult = sourceScrapper.scrap(url);
        if (!sourceScrappingResult.isSuccessful()) {
            return Collections.emptyList();
        }
        return getPageUrls(url, sourceScrapper, sourceScrappingResult)
                .stream()
                .map(sourceScrapper::scrap)
                .filter(SourceScrappingResult::isSuccessful)
                .flatMap(e -> getBookElements(e.getSource()).stream())
                .map(e -> getLink(href(e)))
                .collect(Collectors.toList());
    }

    private ArrayList<String> getPageUrls(String url, SourceScrapper sourceScrapper, SourceScrappingResult sourceScrappingResult) {
        Document source = sourceScrappingResult.getSource();
        Integer pages = getPageQuantity(source);

        ArrayList<String> pageUrls = new ArrayList<>();
        pageUrls.add(url);

        for (int i = 1; i < pages; i++) {
            String nextUrl = getNextUrl(source);
            pageUrls.add(nextUrl);
            sourceScrappingResult = sourceScrapper.scrap(nextUrl);
            if (!sourceScrappingResult.isSuccessful()) {
                break;
            }
            source = sourceScrappingResult.getSource();
        }
        return pageUrls;
    }

    private String getNextUrl(Document source) {
        return getLink(source
                .getElementsByAttributeValue("class", "pages-list")
                .get(ZERO_INDEX)
                .getElementsByAttributeValue("class", "page page--next")
                .attr("href"));
    }

    private Integer getPageQuantity(Document source) {
        return Integer.valueOf(source
                .getElementsByAttributeValue("class", "pages-list")
                .get(ZERO_INDEX)
                .children()
                .get(SIX_INDEX)
                .text());
    }

    private Elements getBookElements(Document source) {
        return source.getElementsByAttributeValue("class", "product-tile-title");
    }

    private String getLink(String i) {
        return "http://www.publio.pl" + i;
    }

    private String href(Element e) {
        return e.getAllElements().attr("href");
    }
}

