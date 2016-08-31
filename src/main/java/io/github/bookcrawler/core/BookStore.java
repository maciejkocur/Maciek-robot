package io.github.bookcrawler.core;

import io.github.bookcrawler.core.impl.JsoupSourceScrapper;
import io.github.bookcrawler.core.impl.empik.EmpikBookInfoParser;
import io.github.bookcrawler.core.impl.empik.EmpikBookLinksCrawler;
import io.github.bookcrawler.core.impl.empik.EmpikBooksExtractor;
import io.github.bookcrawler.core.impl.helionparser.HelionBookExtractor;
import io.github.bookcrawler.core.impl.helionparser.HelionBookParser;
import io.github.bookcrawler.core.impl.packtparser.PacktBookExtractor;
import io.github.bookcrawler.core.impl.packtparser.PacktBookInfoParser;
import io.github.bookcrawler.core.impl.packtparser.PacktBooksCrawler;
import io.github.bookcrawler.core.impl.publio.PublioBookInfoParser;
import io.github.bookcrawler.core.impl.publio.PublioBookLinksCrawler;
import io.github.bookcrawler.core.impl.publio.PublioBooksExtractor;

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
            return new EmpikBooksExtractor(new JsoupSourceScrapper());
        }

        @Override
        public BooksLinkCrawler crawler() {
            return new EmpikBookLinksCrawler();
        }

        @Override
        public BookInfoParser parser() {
            return new EmpikBookInfoParser();
        }
    },

    PACKT {
        @Override
        public String startUrl() {
            return "https://www.packtpub.com/packt/offers/free-learning/";
        }

        @Override
        public String domainUrl() {
            return "https://www.packtpub.com";
        }

        @Override
        public BookExtractor extractor() {
            return new PacktBookExtractor(new JsoupSourceScrapper());
        }

        @Override
        public BooksLinkCrawler crawler() {
            return new PacktBooksCrawler();
        }

        @Override
        public BookInfoParser parser() {
            return new PacktBookInfoParser();
        }
    },
    HELION {
        @Override
        public String startUrl() {
            return "http://helion.pl/promocja-dnia";
        }

        @Override
        public String domainUrl() {
            return "http://helion.pl";
        }

        @Override
        public BookExtractor extractor() {
            return new HelionBookExtractor(new JsoupSourceScrapper());
        }

        @Override
        public BooksLinkCrawler crawler() {
            //empty, not used
            return null;
        }

        @Override
        public BookInfoParser parser() {
            return new HelionBookParser();
        }
    },
    PUBLIO {
        @Override
        public String startUrl() {
            return "http://www.publio.pl/szukaj,promocja.html?sections=EBOOK";
        }

        @Override
        public String domainUrl() {
            return "http://www.publio.pl";
        }

        @Override
        public BookExtractor extractor() {
            return new PublioBooksExtractor(new JsoupSourceScrapper());
        }

        @Override
        public BooksLinkCrawler crawler() {

            return new PublioBookLinksCrawler();
        }

        @Override
        public BookInfoParser parser() {
            return new PublioBookInfoParser();
        }
    };

    public abstract String startUrl();

    public abstract String domainUrl();

    public abstract BookExtractor extractor();

    public abstract BooksLinkCrawler crawler();

    public abstract BookInfoParser parser();
}
