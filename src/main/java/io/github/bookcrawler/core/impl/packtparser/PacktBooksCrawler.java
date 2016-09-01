package io.github.bookcrawler.core.impl.packtparser;

import io.github.bookcrawler.core.BooksLinkCrawler;
import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import org.jsoup.nodes.Document;

import java.util.Collections;
import java.util.List;

public class PacktBooksCrawler implements BooksLinkCrawler {

    private static final String BOOK_LINK_CLASS_NAME = "dotd-main-book-image";

    @Override
    public List<String> crawl(String url, SourceScrapper sourceScrapper) {
        SourceScrappingResult sourceScrappingResult = sourceScrapper.scrap(url);
        if (sourceScrappingResult.isSuccessful()) {
            Document document = sourceScrappingResult.getSource();
            String bookLink = document.getElementsByClass(BOOK_LINK_CLASS_NAME).select("a").first().attr("abs:href");
            return Collections.singletonList(bookLink);
        }
        return Collections.emptyList();
    }
}
