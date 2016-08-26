package io.github.bookcrawler.core;

import io.github.bookcrawler.entities.BookInfo;

import java.util.Collection;

public interface BookInfoParser {

    Collection<BookInfo> parse(Collection<String> urls);
}
