package io.github.bookcrawler.core;

import java.util.Collection;

public interface Crawler {

    Collection<String> crawl(BookStore bookStore);
}
