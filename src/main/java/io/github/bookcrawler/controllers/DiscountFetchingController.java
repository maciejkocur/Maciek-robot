package io.github.bookcrawler.controllers;


import io.github.bookcrawler.cache.DatabaseCacheForDifferentLibraries;
import io.github.bookcrawler.core.DiscountFetchingService;
import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.repositories.BookInfoRepository;
import io.github.bookcrawler.utilities.Library;
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

    @Autowired
    DiscountFetchingService discountFetchingService;

    @Autowired
    BookInfoRepository bookInfoRepository;

    @GetMapping("/")
    public String hello() throws IOException {
        return "index";
    }

    @GetMapping("/fetch/{library}")
    public String fetchBooks(@PathVariable String library, Model model) throws IOException {
        List<BookInfo> bookInfoList = databaseCacheForDifferentLibraries.getBookInfosFromLibrary(library);
        if (bookInfoList.isEmpty()) {
            bookInfoList = bookInfoRepository.findByLibraryAndInputDate(library, new java.sql.Date(new java.util.Date().getTime()));
            databaseCacheForDifferentLibraries.putBookInfoFromLibrary(library, bookInfoList);
            model.addAttribute("isEmpty", "Please wait! Parser is working.....Refresh page in a couple of minutes!");
        } else {
            model.addAttribute("isEmpty", "");
        }
        model.addAttribute("books", bookInfoList);
        return "booksResult";
    }

    @GetMapping("/onDemand/{library}")
    public String fetchOnDemand(@PathVariable String library, Model model) {
        List<BookInfo> bookInfoList = discountFetchingService.getBooksFromLibrary(Library.valueOf(library));
        model.addAttribute("books", bookInfoList);
        return "bookOnDemand";
    }
}
