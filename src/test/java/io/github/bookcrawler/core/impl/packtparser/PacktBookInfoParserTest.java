package io.github.bookcrawler.core.impl.packtparser;

import io.github.bookcrawler.cache.AuthorsCache;
import io.github.bookcrawler.core.impl.SourceScrappingResult;
import io.github.bookcrawler.entities.Author;
import io.github.bookcrawler.entities.BookInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PacktBookInfoParserTest {

    @Test
    public void testParsingBookElementsFromDocument() throws IOException {

        //given
        PacktBookInfoParser packtBookInfoParser = new PacktBookInfoParser();
        AuthorsCache authorsCache = mock(AuthorsCache.class);
        packtBookInfoParser.authorsCache = authorsCache;

        String expectedTitle = "Mastering Apache Cassandra";
        String expectedAuthor = "Nishant Neeraj";
        String expectedDescription = "Learn how to build more robust, scalable databases using Cassandra. " +
                "From beginners to intermediates, this practical guide covers all the bases " +
                "to help you get the most out of your infrastructure and using the full potential of Cassandra.";
        String expectedPrice = "free";
        String expectedLibrary = "Packt";
        Document source = Jsoup.parse(new File("src/test/java/io/github/bookcrawler/core/" +
                "impl/packtparser/mastering_casandra_packt.html"), "UTF-8");
        SourceScrappingResult sourceScrappingResult = mock(SourceScrappingResult.class);

        when(authorsCache.getAuthorFromCache(expectedAuthor)).thenReturn(new Author(expectedAuthor));
        when(sourceScrappingResult.isSuccessful()).thenReturn(true);
        when(sourceScrappingResult.getSource()).thenReturn(source);

        //when
        BookInfo book = packtBookInfoParser.parse(sourceScrappingResult);

        //then
        assertThat(book.getTitle(), is(expectedTitle));
        assertThat(book.getAuthor(), is(expectedAuthor));
        assertThat(book.getDescription(), is(expectedDescription));
        assertThat(book.getPrice(), is(expectedPrice));
        assertThat(book.getLibrary(), is(expectedLibrary));
    }
}