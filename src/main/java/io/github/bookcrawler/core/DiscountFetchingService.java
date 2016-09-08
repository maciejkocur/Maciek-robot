package io.github.bookcrawler.core;

import io.github.bookcrawler.entities.BookInfo;
import io.github.bookcrawler.utilities.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.github.bookcrawler.utilities.Library.*;

@Component
public class DiscountFetchingService {

    @Autowired
    private BookStore empikBookStore;

    @Autowired
    private BookStore publioBookStore;

    @Autowired
    private BookStore packtBookStore;

    @Autowired
    private BookStore helionBookStore;

    private Map<Library, BookStore> bookStores;

    @PostConstruct
    public void initBookStores() {
        bookStores = new HashMap<>();
        bookStores.put(EMPIK, empikBookStore);
        bookStores.put(PUBLIO, publioBookStore);
        bookStores.put(HELION, helionBookStore);
        bookStores.put(PACT, packtBookStore);
    }

    private List<BookInfo> fetch(Collection<BookStore> bookStores) {
        return bookStores.parallelStream()
                .flatMap(bookStore -> bookStore.extractor().extract().parallelStream())
                .collect(Collectors.toList());
    }

    public List<BookInfo> getAllBooks() {
        return fetch(bookStores.values());
    }

    public List<BookInfo> getBooksFromLibrary(Library library) {
        return bookStores.get(library).extractor().extract();
    }

}
