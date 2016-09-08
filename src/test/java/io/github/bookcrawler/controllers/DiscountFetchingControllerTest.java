package io.github.bookcrawler.controllers;

import io.github.bookcrawler.cache.DatabaseCacheForDifferentLibraries;
import io.github.bookcrawler.core.DiscountFetchingService;
import io.github.bookcrawler.repositories.BookInfoRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//It's not a unit test. It starts server and check how controller works.
public class DiscountFetchingControllerTest {

    private DiscountFetchingController controller;

    @BeforeMethod
    public void prepareController() {
        controller = new DiscountFetchingController();
        DatabaseCacheForDifferentLibraries mockCache = mock(DatabaseCacheForDifferentLibraries.class);
        when(mockCache.getAllBookInfos()).thenReturn(Collections.emptyList());
        BookInfoRepository mockRepo = mock(BookInfoRepository.class);
        when(mockRepo.findByLibraryAndInputDate(anyString(),any())).thenReturn(Collections.emptyList());

        DiscountFetchingService mockFetchingService = mock(DiscountFetchingService.class);
        when(mockFetchingService.getBooksFromLibrary(any())).thenReturn(Collections.emptyList());
        controller.databaseCacheForDifferentLibraries = mockCache;
        controller.bookInfoRepository = mockRepo;
        controller.discountFetchingService = mockFetchingService;

    }

    @DataProvider
    public Object[][] requestsAndJSP() {
        return new Object[][]{
                {"/", "index"},
                {"/fetch/EMPIK", "booksResult"},
                {"/fetch/PUBLIO", "booksResult"},
                {"/fetch/PACT", "booksResult"},
                {"/fetch/HELION", "booksResult"},
                {"/onDemand/EMPIK", "bookOnDemand"},
                {"/onDemand/PUBLIO", "bookOnDemand"},
                {"/onDemand/PACT", "bookOnDemand"},
                {"/onDemand/HELION", "bookOnDemand"},
        };
    }

    @Test(dataProvider = "requestsAndJSP")
    public void testIndexPage(String request, String JSP) throws Exception {
        //given BeforeMethod

        //when
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(request)).andExpect(MockMvcResultMatchers.view().name(JSP));
    }
}
