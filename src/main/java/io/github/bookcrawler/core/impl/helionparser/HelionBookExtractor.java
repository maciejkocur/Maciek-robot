package io.github.bookcrawler.core.impl.helionparser;

import io.github.bookcrawler.core.BookExtractor;
import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.entities.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class HelionBookExtractor implements BookExtractor {

    @Autowired
    private HelionBookParser helionBookParser;

    @Autowired
    private SourceScrapper jsoupSourceScapper;


    @Override
    public List<BookInfo> extract() {
        return Collections.singletonList(
                helionBookParser.parse(
                        jsoupSourceScapper.scrap(
                                "http://helion.pl/promocja-dnia")));
    }
}
