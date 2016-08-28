package io.github.bookcrawler.core.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SourceScrappingResult {
    private final Document source;
    private final SourceScrappingStatus status;


    SourceScrappingResult(Document source, SourceScrappingStatus status) {
        this.source = source;
        this.status = status;
    }

    //TODO: not used method
    public boolean isSuccesful() {
        return status == SourceScrappingStatus.SUCCESS;
    }

    public Element getSource() {
        return source;

    }
}
