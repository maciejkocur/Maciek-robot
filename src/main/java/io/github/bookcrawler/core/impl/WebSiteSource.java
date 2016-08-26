package io.github.bookcrawler.core.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebSiteSource {
    private Document value;

    private WebSiteSource(Document source) {
        this.value = source;
    }

    private WebSiteSource() {
    }

    public static WebSiteSource of(Document source) {
        return new WebSiteSource(source);
    }

    public static WebSiteSource empty() {
        return new WebSiteSource();
    }

    public Element getValue() {
//        TODO: null
        return value;
    }
}
