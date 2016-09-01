package io.github.bookcrawler.controllers;

import io.github.bookcrawler.core.DiscountFetchingService;
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
    DiscountFetchingService discountFetchingService;

    @GetMapping("/")
    public String hello(Model model) throws IOException {
        List<BookInfo> bookInfos = new DiscountFetchingService().getAllBooks();
        model.addAttribute("books", bookInfos);
        return "index";
    }

    @GetMapping("/fetch/{library}")
    public String fetchBooks(@PathVariable String library, Model model) throws IOException {
        List<BookInfo> bookInfos =discountFetchingService.getAllBooks();
        bookInfos.addAll(discountFetchingService.getAllBooks());
        model.addAttribute("books", bookInfos);
        return "booksResult";
    }
}
