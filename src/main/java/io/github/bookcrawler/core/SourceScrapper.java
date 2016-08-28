package io.github.bookcrawler.core;

import io.github.bookcrawler.core.impl.SourceScrappingResult;

public interface SourceScrapper {

    SourceScrappingResult scrap(String url);
}
