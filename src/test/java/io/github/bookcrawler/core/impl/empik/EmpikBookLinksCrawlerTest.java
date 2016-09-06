package io.github.bookcrawler.core.impl.empik;

import io.github.bookcrawler.core.SourceScrapper;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmpikBookLinksCrawlerTest {

    @Test
    public void crawlEmpikSite() throws Exception {

        // given
        EmpikBookLinksCrawler empikBookLinksCrawler = new EmpikBookLinksCrawler();
        String testUrl = "test";

        SourceScrapper sourceScrapperMock = mock(SourceScrapper.class);
        SourceScrappingResult sourceScrappingResultMock = mock(SourceScrappingResult.class);

        when(sourceScrapperMock.scrap(testUrl))
                .thenReturn(sourceScrappingResultMock);
        when(sourceScrapperMock.scrap("http://www.empik.com/ebooki/kryminaly-i-sensacje"))
                .thenReturn(sourceScrappingResultMock);

        when(sourceScrappingResultMock.isSuccessful())
                .thenReturn(true);

        Document mainSource = Jsoup.parse(new File("src/test/java/io/github/bookcrawler/core/impl/empik/empikCrawlerMainTest.html"), "UTF-8");
        Document sectionSource = Jsoup.parse(new File("src/test/java/io/github/bookcrawler/core/impl/empik/empikCrawlerSectionTest.html"), "UTF-8");
        when(sourceScrappingResultMock.getSource())
                .thenReturn(mainSource)
                .thenReturn(sectionSource);

        // when
        List<String> actualUrls = empikBookLinksCrawler.crawl(testUrl, sourceScrapperMock);

        // then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUrls.size(), 40);
        softAssert.assertEquals(actualUrls.get(0), "http://www.empik.com/uwiklanie-miloszewski-zygmunt,p1046125476,ebooki-i-mp3-p");
        softAssert.assertEquals(actualUrls.get(1), "http://www.empik.com/gniew-miloszewski-zygmunt,p1102035963,ebooki-i-mp3-p");
        softAssert.assertEquals(actualUrls.get(2), "http://www.empik.com/ziarno-prawdy-miloszewski-zygmunt,p1045554060,ebooki-i-mp3-p");
        softAssert.assertEquals(actualUrls.get(39), "http://www.empik.com/piata-kobieta-mankell-henning,p1046149467,ebooki-i-mp3-p");
        softAssert.assertAll();
    }
}