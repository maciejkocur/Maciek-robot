package io.github.bookcrawler;
import io.github.bookcrawler.entities.BookInfo;

import java.io.IOException;

public interface BookExtractor {

    Iterable<BookInfo> extract() throws IOException;
}
