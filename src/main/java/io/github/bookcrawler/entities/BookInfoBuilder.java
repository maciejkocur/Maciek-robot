package io.github.bookcrawler.entities;

import io.github.bookcrawler.cache.AuthorsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Set;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BookInfoBuilder {

    @Autowired
    private AuthorsCache authorsCache;

    String title;
    String description;
    String price;
    String library;
    String url;
    Date inputDate;
    Author author;
    Set<Tag> tags;

    public BookInfoBuilder title(String title) {
        this.title = title;
        return this;
    }

    public BookInfoBuilder description(String description) {
        this.description = description;
        return this;
    }


    public BookInfoBuilder price(String price) {
        this.price = price;
        return this;
    }

    public BookInfoBuilder library(String library) {
        this.library = library;
        return this;
    }

    public BookInfoBuilder url(String url) {
        this.url = url;
        return this;
    }

    public BookInfoBuilder author(String authorName) {
        this.author = authorsCache.getAuthorFromCache(authorName);
        return this;
    }

    public BookInfoBuilder tags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public BookInfoBuilder inputDate(Long inputDate) {
        this.inputDate = new Date(inputDate);
        return this;
    }

    public BookInfo build() {
        return new BookInfo(this);
    }

}
