package io.github.bookcrawler.core.impl.publio;

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

public class PublioBookInfoParserTest {


    @DataProvider
    public Object[][] books() {
        return new Object[][]{
                {"src/test/java/io/github/bookcrawler/core/impl/publio/publioParserTest1.html", "Annapurna Góra kobiet", "Arlene Blum", "Opis Boleśnie szczery opis pierwszego ko", "24,29 zł", "PUBLIO"},
                {"src/test/java/io/github/bookcrawler/core/impl/publio/publioParserTest2.html", "Baśniobór", "Brandon Mull", "Opis Pierwsze miejsce na liście książkow", "15,53 zł", "PUBLIO"}
        };
    }

    @Test(dataProvider = "books")
    public void parsesPublioBook(String path, String title, String author, String description, String price, String library) throws IOException {
        // given
        PublioBookInfoParser publioBookInfoParser = new PublioBookInfoParser();
        AuthorsCache authorsCache = mock(AuthorsCache.class);
        publioBookInfoParser.authorsCache = authorsCache;
        when(authorsCache.getAuthorFromCache(author)).thenReturn(new Author(author));
        Document testedSource = Jsoup.parse(new File(path), "UTF-8");

        SourceScrappingResult sourceScrappingResult = new SourceScrappingResult(testedSource, SourceScrappingStatus.SUCCESS);

        // when
        BookInfo parseBookInfo = publioBookInfoParser.parse(sourceScrappingResult);

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