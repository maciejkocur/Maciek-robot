package io.github.bookcrawler.controllers;

import io.github.bookcrawler.core.DiscountFetchingService;
import io.github.bookcrawler.entities.BookInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class DiscountFetchingController {

    @GetMapping("/")
    public String hello(Model model) throws IOException {
        List<BookInfo> bookInfos = new DiscountFetchingService().getAllBooks();
        model.addAttribute("books", bookInfos);

        return "index";
    }
}
