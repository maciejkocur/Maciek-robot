package io.github.bookcrawler.controllers;

import io.github.bookcrawler.cache.DatabaseCacheForDifferentLibraries;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//It's not a unit test. It starts server and check how controller works.
public class DiscountFetchingControllerTest {

    private DiscountFetchingController controller;

    @BeforeMethod
    public void prepareController(){
        controller = new DiscountFetchingController();
        DatabaseCacheForDifferentLibraries mockCache = mock(DatabaseCacheForDifferentLibraries.class);
        when(mockCache.getAllBookInfos()).thenReturn(Collections.EMPTY_LIST);
        controller.databaseCacheForDifferentLibraries = mockCache;
    }

    @Test
    public void testIndexPage() throws Exception {
        //given BeforeMethod

        //when
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    public void testResultWithBooksPage() throws Exception {
        //given BeforeMethod

        //when
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/fetch/Empik")).andExpect(MockMvcResultMatchers.view().name("booksResult"));
    }
}
