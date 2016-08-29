package io.github.bookcrawler.core;

import io.github.bookcrawler.entities.BookInfo;

import java.util.Collection;

public interface BookExtractor {

    Collection<BookInfo> extract();
}
