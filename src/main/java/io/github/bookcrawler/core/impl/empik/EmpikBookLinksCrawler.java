package io.github.bookcrawler.core.impl.empik;

import io.github.bookcrawler.core.BooksLinkCrawler;
import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmpikBookLinksCrawler implements BooksLinkCrawler {


    @Override
    public List<String> crawl(String url, SourceScrapper sourceScrapper) {

        SourceScrappingResult sourceScrappingResult = sourceScrapper.scrap(url);
        if (!sourceScrappingResult.isSuccessful()) {
            return Collections.emptyList();
        }

        return getSectionElements(sourceScrappingResult.getSource())
                .stream()
                .map(e -> sourceScrapper.scrap(getLink(href(e))))
                .filter(SourceScrappingResult::isSuccessful)
                .map(SourceScrappingResult::getSource)
                .flatMap(e -> getBookElements(e).stream())
                .filter(e -> !href(e).isEmpty())
                .map(e -> getLink(href(e)))
                .collect(Collectors.toList());
    }

    private Elements getBookElements(Document source) {
        return source.getElementsByAttributeValue("class", "productBox-450Pic");
    }

    private Elements getSectionElements(Document source) {
        return source.getElementsByAttributeValue("class", "more_lnk");
    }

    private String getLink(String i) {
        return "http://www.empik.com" + i;
    }

    private String href(Element e) {
        return e.attr("href");
    }
}
