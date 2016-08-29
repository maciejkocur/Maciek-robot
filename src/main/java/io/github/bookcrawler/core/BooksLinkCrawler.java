package io.github.bookcrawler.core;

import java.util.Collection;

@FunctionalInterface
public interface BooksLinkCrawler {

    Collection<String> crawl(String url, SourceScrapper sourceScrapper);
}
