package io.github.bookcrawler.controllers;

import io.github.bookcrawler.core.DiscountFetchingService;
import io.github.bookcrawler.entities.BookInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class DiscountFetchingController {

    @GetMapping("/")
    public String hello(Model model) throws IOException {
        List<BookInfo> list = new ArrayList<>();

        Collection<BookInfo> bookInfos = new DiscountFetchingService().getAllBooks();
        
        bookInfos.stream().forEach(list::add);

        model.addAttribute("books", list);

        return "index";
    }
}
