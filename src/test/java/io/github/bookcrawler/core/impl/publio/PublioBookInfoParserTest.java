package io.github.bookcrawler.core.impl.publio;

import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

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
        Document testedSource = Jsoup.parse(new File(path), "UTF-8");

        SourceScrappingResult sourceScrappingResultMock = mock(SourceScrappingResult.class);
        when(sourceScrappingResultMock.isSuccessful()).thenReturn(true);
        when(sourceScrappingResultMock.getSource()).thenReturn(testedSource);

        // when
        BookInfo parseBookInfo = publioBookInfoParser.parse(sourceScrappingResultMock);

        // then
        assertEquals(parseBookInfo.getTitle(), title);
        assertEquals(parseBookInfo.getAuthor(), author);
        assertEquals(parseBookInfo.getDescription().substring(0, 40), description);
        assertEquals(parseBookInfo.getPrice(), price);
        assertEquals(parseBookInfo.getLibrary(), library);
    }

}