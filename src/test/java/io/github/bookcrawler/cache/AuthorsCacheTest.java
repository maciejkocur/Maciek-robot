//package io.github.bookcrawler.cache;
//
//import io.github.bookcrawler.config.DatabaseConfiguration;
//import io.github.bookcrawler.config.ServletContextConfig;
//import io.github.bookcrawler.entities.Author;
//import io.github.bookcrawler.repositories.AuthorsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.assertTrue;
//
//
//@WebAppConfiguration
//@ContextConfiguration(classes = {DatabaseConfiguration.class, ServletContextConfig.class})
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@Transactional
//public class AuthorsCacheTest extends AbstractTestNGSpringContextTests {
//
//    @Autowired
//    private AuthorsRepository authorsRepository;
//
//    @Autowired
//    private AuthorsCache authorsCache;
//
//    @Test(dataProvider = "authors")
//    public void testIfAllAuthorsFromDbAreInCache(List<Author> authors) {
//        //arrange
//        authorsRepository.save(authors);
//
//        //act
//        authorsCache.saveAuthorsFromDBInCache();
//
//        //assert
//        int numberOfAuthorsInDB = authors.size();
//        int numberOfAuthorsInCache = authorsCache.authors.size();
//        assertEquals(numberOfAuthorsInDB, numberOfAuthorsInCache, "All authors from DB should be in cache");
//    }
//
//
//    @Test(dataProvider = "authors")
//    public void testIfRightAuthorsAreInCache(List<Author> authors) {
//        //arrange
//        authorsRepository.save(authors);
//
//        //act
//        authorsCache.saveAuthorsFromDBInCache();
//
//        //assert
//        authors.stream().forEach(author -> assertTrue(authorsCache.authors.contains(author)));
//    }
//
//    @DataProvider
//    Object[][] authors() {
//        return new Object[][]{{new ArrayList<>()}, {getAutrhors()}};
//    }
//
//
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void testGettingAuthorWithNameNull() {
//        authorsCache.getAuthorFromCache(null);
//    }
//
//    private List<Author> getAutrhors() {
//        List<Author> authors = new ArrayList<>();
//        authors.add(new Author("Adam Stefański"));
//        authors.add(new Author("Dawid Zaremba"));
//        authors.add(new Author("Tom Clancy"));
//        authors.add(new Author("John Grisham"));
//        authors.add(new Author("Henryk Sienkiewicz"));
//        return authors;
//    }
//}
//
//
