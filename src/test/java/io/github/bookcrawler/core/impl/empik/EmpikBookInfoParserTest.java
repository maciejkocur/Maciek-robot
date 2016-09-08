package io.github.bookcrawler.core.impl.empik;

import io.github.bookcrawler.cache.AuthorsCache;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.core.impl.SourceScrappingStatus;
import io.github.bookcrawler.entities.Author;
import io.github.bookcrawler.entities.BookInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmpikBookInfoParserTest {


    @DataProvider
    public Object[][] books() {
        return new Object[][]{
                {"src/test/java/io/github/bookcrawler/core/impl/empik/empikParserTest1.html", "Uwikłanie", "Miłoszewski Zygmunt", "Nagroda Wielkiego Kalibru dla najlepszej", "19,19 zł", "http://www.empik.com", "EMPIK"},
                {"src/test/java/io/github/bookcrawler/core/impl/empik/empikParserTest2.html", "Człowiek, który chciał wszystko wiedzieć", "Mishani Dror A", "Tajemnicze morderstwo, traumy zgwałconyc", "19,59 zł", "http://www.empik.com", "EMPIK"}
        };
    }

    @Test(dataProvider = "books")
    public void parsesEmpikBook(String path, String title, String author, String description, String price, String url, String library) throws IOException {

        // given
        EmpikBookInfoParser empikBookInfoParser = new EmpikBookInfoParser();
        AuthorsCache authorsCache = mock(AuthorsCache.class);
        empikBookInfoParser.authorsCache = authorsCache;
        Document testedSource = Jsoup.parse(new File(path), "UTF-8");

        when(authorsCache.getAuthorFromCache(author)).thenReturn(new Author(author));

        SourceScrappingResult sourceScrappingResult = new SourceScrappingResult(testedSource, SourceScrappingStatus.SUCCESS);

        // when
        BookInfo parseBookInfo = empikBookInfoParser.parse(sourceScrappingResult);

        // then
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(parseBookInfo.getTitle(), title);
        softAssert.assertEquals(parseBookInfo.getAuthor(), author);
        softAssert.assertEquals(parseBookInfo.getDescription().substring(0, 40), description);
        softAssert.assertEquals(parseBookInfo.getPrice(), price);
        softAssert.assertEquals(parseBookInfo.getLibrary(), library);
        softAssert.assertAll();
    }
}