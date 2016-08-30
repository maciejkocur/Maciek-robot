package io.github.bookcrawler.entities;

import java.util.Set;

public class BookInfoBuilder {

    String title;
    String description;
    String price;
    String library;
    String url;
    String author;
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

    public BookInfoBuilder author(String author) {
        this.author = author;
        return this;
    }

    public BookInfoBuilder tags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public BookInfo build() {
        return new BookInfo(this);
    }

}
