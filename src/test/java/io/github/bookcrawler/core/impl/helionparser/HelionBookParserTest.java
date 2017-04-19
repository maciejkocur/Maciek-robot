package io.github.bookcrawler.core.impl.helionparser;

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
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebAppConfiguration
@ContextConfiguration(classes = {ServletContextConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class HelionBookParserTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private HelionBookParser helionBookParser;

    @Test
    public void testParsingBookElementsFromDocument() throws IOException {
        //given

        SourceScrappingResult sourceScrappingResult = mock(SourceScrappingResult.class);
        Document source = Jsoup.parse(new File("src/test/java/io/github/bookcrawler/core/" +
                "impl/helionparser/helion_promo_of_the_day.html"), "UTF-8");

        //when
        String expectedTitle = "Sprint projektowy. Tworzenie produkt�w cyfrowych";
        String expectedAuthor = "Richard Banfield C. Todd Lombardo Trace Wax";
        String expectedDescription = "Sprintem do sukcesu!";
        String expectedPrice = "30% discount";
        String expectedLibrary = "Helion";
        when(sourceScrappingResult.isSuccessful()).thenReturn(true);
        when(sourceScrappingResult.getSource()).thenReturn(source);
        BookInfo book = helionBookParser.parse(sourceScrappingResult);

        //then
        assertThat(book.getTitle(), is(expectedTitle));
        assertThat(book.getAuthor(), is(expectedAuthor));
        assertThat(book.getDescription(), is(expectedDescription));
        assertThat(book.getPrice(), is(expectedPrice));
        assertThat(book.getLibrary(), is(expectedLibrary));
    }


}