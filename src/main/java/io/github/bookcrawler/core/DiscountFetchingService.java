package io.github.bookcrawler.core;

import io.github.bookcrawler.entities.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DiscountFetchingService {

    @Autowired
    private BookStore empikBookStore;

    private Set<BookStore> bookStores;

    @PostConstruct
    public void initBookStores() {
        bookStores = new HashSet<>();
        bookStores.add(empikBookStore);
    }

    private List<BookInfo> fetch(Set<BookStore> bookStores) {
        return bookStores.parallelStream()
                .flatMap(bookStore -> bookStore.extractor().extract().parallelStream())
                .collect(Collectors.toList());
    }

    public List<BookInfo> getAllBooks() {
        return fetch(bookStores);
    }

    public List<BookInfo> getEmpikBooks() {
        return empikBookStore.extractor().extract();
    }
}
