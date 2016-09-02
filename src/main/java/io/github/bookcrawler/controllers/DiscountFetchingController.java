package io.github.bookcrawler.controllers;


import io.github.bookcrawler.cache.DatabaseCacheForDifferentLibraries;
import io.github.bookcrawler.entities.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

@Controller
public class DiscountFetchingController {

    @Autowired
    DatabaseCacheForDifferentLibraries databaseCacheForDifferentLibraries;

    @GetMapping("/")
    public String hello() throws IOException {
        return "index";
    }

    @GetMapping("/fetch/{library}")
    public String fetchBooks(@PathVariable String library, Model model) throws IOException {
        List<BookInfo> bookInfoList = databaseCacheForDifferentLibraries.getBookInfosFromLibrary(library);
        model.addAttribute("books", bookInfoList);
        return "booksResult";
    }
}
