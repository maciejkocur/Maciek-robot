package io.github.bookcrawler.core.impl;

import org.jsoup.Jsoup;

import java.io.IOException;


public class SourceScrapper {
    public static WebSiteSource scrap(String url) {
        try {
            return WebSiteSource.of(Jsoup.connect(url).timeout(0).get());
        } catch (IOException e) {
            // TODO: add logs
            e.printStackTrace();
            return WebSiteSource.empty();
        }
    }
}
