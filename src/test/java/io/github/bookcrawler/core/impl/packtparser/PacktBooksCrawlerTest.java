package io.github.bookcrawler.core.impl.packtparser;

import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PacktBooksCrawlerTest {

    @Test
    public void checkIfCrawlerReturnsLinkToBook() throws IOException {
        //given
        Document source = Jsoup.parse(new File("src/test/java/io/github/bookcrawler/core/impl/packtparser/packt_free_learning.html"), "UTF-8");
        SourceScrapper sourceScrapperMock = mock(SourceScrapper.class);
        PacktBooksCrawler packtBooksCrawler = new PacktBooksCrawler();
        SourceScrappingResult sourceScrappingResultMock = mock(SourceScrappingResult.class);

        //when
        when(sourceScrapperMock.scrap("")).thenReturn(sourceScrappingResultMock);
        when(sourceScrappingResultMock.isSuccessful()).thenReturn(true);
        when(sourceScrappingResultMock.getSource()).thenReturn(source);

        //then
        assertThat((packtBooksCrawler.crawl("", sourceScrapperMock)).get(0), is(""));
    }

}