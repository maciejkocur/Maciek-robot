package io.github.bookcrawler.core.impl.packtparser;

import io.github.bookcrawler.core.BookExtractor;
import io.github.bookcrawler.core.BookStore;
import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PacktBookExtractor implements BookExtractor {

    @Autowired
    private PacktBookInfoParser packtBookInfoParser;

    @Autowired
    private BookStore packtBookStore;

    @Autowired
    private SourceScrapper jsoupSourceScapper;

    @Override
    public List<BookInfo> extract() {
        return packtBookStore.crawler().crawl(packtBookStore.startUrl(), jsoupSourceScapper)
                .stream()
                .map(jsoupSourceScapper::scrap)
                .filter(SourceScrappingResult::isSuccessful)
                .map(packtBookInfoParser::parse)
                .collect(Collectors.toList());
    }
}
