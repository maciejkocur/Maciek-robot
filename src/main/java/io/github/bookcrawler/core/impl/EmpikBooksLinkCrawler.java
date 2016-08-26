package io.github.bookcrawler.core.impl;

import io.github.bookcrawler.core.BookStore;
import io.github.bookcrawler.core.Crawler;
import org.jsoup.nodes.Element;

import java.util.Collection;
import java.util.LinkedHashSet;

public class EmpikBooksLinkCrawler implements Crawler {

    @Override
    public Collection<String> crawl(BookStore bookStore) {
        Collection<String> sectionUrls = new LinkedHashSet<>();
        for (Element element : SourceScrapper.scrap(bookStore.startUrl()).getValue().getElementsByAttributeValue("class", "more_lnk")) {
            sectionUrls.add(bookStore.domainUrl() + element.attr("href"));
        }

        Collection<String> booksUrls = new LinkedHashSet<>();
        for (String sectionUrl : sectionUrls) {
            for (Element bookElement : SourceScrapper.scrap(sectionUrl).getValue().getElementsByAttributeValue("class", "productBox-450Pic")) {
                String link = bookElement.attr("href");
                if (link.equals("")) {
                    continue;
                }
                booksUrls.add("http://www.empik.com" + link);
            }
        }
        return booksUrls;
    }
}
