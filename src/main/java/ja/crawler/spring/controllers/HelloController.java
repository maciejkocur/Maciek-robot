package ja.crawler.spring.controllers;

import ja.crawler.BookExtractor;
import ja.crawler.Entities.BookInfo;
import ja.crawler.EmpikBookExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(Model model) throws IOException {
        List<BookInfo> list = new ArrayList<>();
        BookExtractor empikBookExtractor = new EmpikBookExtractor();
        Iterable<BookInfo> bookInfos = empikBookExtractor.extract();
        bookInfos.forEach(bookInfo -> list.add(bookInfo));
        String title = list.get(0).getTitle();
        model.addAttribute("title",title);

        //model.addAttribute("name", "Result of researching books");

        return "index";
    }
}
