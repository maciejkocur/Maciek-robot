package io.github.bookcrawler.core.impl;

import org.jsoup.nodes.Document;

public class SourceScrappingResult {
    private final Document source;
    private final SourceScrappingStatus status;

    SourceScrappingResult(Document source, SourceScrappingStatus status) {
        this.source = source;
        this.status = status;
    }

    public boolean isSuccessful() {
        return status == SourceScrappingStatus.SUCCESS;
    }

    public Document getSource() {
        return source;

    }
}
