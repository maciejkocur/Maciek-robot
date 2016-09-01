package io.github.bookcrawler.core.impl.empik;

import io.github.bookcrawler.config.ServletContextConfig;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.BookInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes = {ServletContextConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class EmpikBookInfoParserTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private EmpikBookInfoParser empikBookInfoParser;

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

        Document testedSource = Jsoup.parse(new File(path), "UTF-8");

        SourceScrappingResult sourceScrappingResultMock = mock(SourceScrappingResult.class);
        when(sourceScrappingResultMock.isSuccessful()).thenReturn(true);
        when(sourceScrappingResultMock.getSource()).thenReturn(testedSource);

        // when
        BookInfo parseBookInfo = empikBookInfoParser.parse(sourceScrappingResultMock);

        // then
        assertEquals(parseBookInfo.getTitle(), title);
        assertEquals(parseBookInfo.getAuthor(), author);
        assertEquals(parseBookInfo.getDescription().substring(0, 40), description);
        assertEquals(parseBookInfo.getPrice(), price);
        assertEquals(parseBookInfo.getUrl(), url);
        assertEquals(parseBookInfo.getLibrary(), library);
    }
}