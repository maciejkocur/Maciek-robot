package io.github.bookcrawler.controllers;

import io.github.bookcrawler.BookExtractor;
import io.github.bookcrawler.EmpikBookExtractor;
import io.github.bookcrawler.entities.BookInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(Model model) throws IOException {
        List<String> list = new ArrayList<>();
        BookExtractor empikBookExtractor = new EmpikBookExtractor();
        Iterable<BookInfo> bookInfos = empikBookExtractor.extract();

        StreamSupport.stream(bookInfos.spliterator(),false).forEach(bookInfo -> list.add(bookInfo.getTitle()));
        model.addAttribute("books", list);

        return "index";
    }
}
