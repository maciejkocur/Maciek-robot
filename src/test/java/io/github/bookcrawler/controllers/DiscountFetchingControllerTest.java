package io.github.bookcrawler.controllers;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//It's not a unit test. It starts server and check how controller works.
public class DiscountFetchingControllerTest {

    private DiscountFetchingController controller;

    @BeforeMethod
    public void prepareController(){
        controller = new DiscountFetchingController();
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
