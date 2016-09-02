package io.github.bookcrawler.controllers;

import io.github.bookcrawler.core.DiscountFetchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class DiscountFetchingController {

    @Autowired
    DiscountFetchingService discountFetchingService;

    @GetMapping("/")
    public String hello() throws IOException {
        return "index";
    }

    @GetMapping("/fetch/{library}")
    public String fetchBooks(@PathVariable String library, Model model) throws IOException {
        model.addAttribute("books", discountFetchingService.getAllBooks());
        return "booksResult";
    }
}
