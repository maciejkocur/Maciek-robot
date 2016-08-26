package io.github.bookcrawler.core;

import io.github.bookcrawler.core.impl.EmpikBookExtractor;

public enum BookStore {
    EMPIK {
        @Override
        public String startUrl() {
            return "http://www.empik.com/ebooki/promocje/";
        }

        @Override
        public String domainUrl() {
            return "http://www.empik.com";
        }

        @Override
        public BookExtractor extractor() {
            return new EmpikBookExtractor();
        }
    };

    public abstract String startUrl();

    public abstract String domainUrl();

    public abstract BookExtractor extractor();
}
