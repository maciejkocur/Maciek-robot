package io.github.bookcrawler.core.impl.publio;

import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PublioBookLinksCrawlerTest {

    @Test
    public void crawlPublio() throws Exception {
        // given
        PublioBookLinksCrawler publioBookLinksCrawler = new PublioBookLinksCrawler();

        SourceScrapper sourceScrapperMock = mock(SourceScrapper.class);
        SourceScrappingResult sourceScrappingResultMock = mock(SourceScrappingResult.class);

        when(sourceScrapperMock.scrap(anyString()))
                .thenReturn(sourceScrappingResultMock);

        when(sourceScrappingResultMock.isSuccessful())
                .thenReturn(true);

        Document firstPageSource = Jsoup.parse(new File("src/test/java/io/github/bookcrawler/core/impl/publio/publioCrawlerFirstPage.html"), "UTF-8");
        Document secondPageSource = Jsoup.parse(new File("src/test/java/io/github/bookcrawler/core/impl/publio/publioCrawlerSecondPage.html"), "UTF-8");
        when(sourceScrappingResultMock.getSource())
                .thenReturn(firstPageSource)
                .thenReturn(firstPageSource)
                .thenReturn(firstPageSource)
                .thenReturn(secondPageSource);

        // when
        List<String> actualUrls = publioBookLinksCrawler.crawl(anyString(), sourceScrapperMock);

        // then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUrls.size(), 40);
        softAssert.assertEquals(actualUrls.get(0), "http://www.publio.pl/100-potwornych-opowiesci-o-pieniadzach-maciej-samcik,p98795.html");
        softAssert.assertEquals(actualUrls.get(1), "http://www.publio.pl/100-xx-50-tom-3-1910-2000,p129119.html");
        softAssert.assertEquals(actualUrls.get(2), "http://www.publio.pl/100-xx-tom-1-1901-1965,p96445.html");
        softAssert.assertEquals(actualUrls.get(39), "http://www.publio.pl/czy-jestes-psychopata-jon-ronson,p105903.html");
        softAssert.assertAll();
    }

}