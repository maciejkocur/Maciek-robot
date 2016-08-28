package io.github.bookcrawler.core;

import io.github.bookcrawler.entities.BookInfo;

import java.util.Collection;

public interface BookInfoParser {
    //TODO: parsers should parse source, String, jSoup Document or Wrapper
    Collection<BookInfo> parse(Collection<String> urls);
}
