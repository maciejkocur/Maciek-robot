package io.github.bookcrawler.cache;

import io.github.bookcrawler.entities.Author;
import io.github.bookcrawler.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorsCache {

    @Autowired
    private AuthorsRepository authorsRepository;

    List<Author> authors = new ArrayList<>();

    void saveAuthorsFromDBInCache() {
        authors = authorsRepository.findAll();
    }

    public Author getAuthorFromCache(String authorName) {
        if (authorName == null) throw new IllegalArgumentException("Null authorName not allowed");
        Optional<Author> optional = authors.stream().
                filter(author -> author.getName().equals(authorName)).
                findAny();
        return optional.isPresent() ? optional.get() : addToAuthorsAndReturn(authorName);
    }

    private Author addToAuthorsAndReturn(String authorName) {
        Author author = new Author(authorName);
        authorsRepository.save(author);
        authors.add(author);
        return author;
    }
}
