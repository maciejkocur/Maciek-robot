package io.github.bookcrawler.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
//TODO remeber to add Date field and create index on it
public class BookInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String title;

    @Column(length = 10000)
    private String description;

    private String price;

    private String library;

    private String url;

    private String author;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Tag> tags;

    private BookInfo() {
    }

    public BookInfo(BookInfoBuilder bookInfoBuilder) {
        this.title = bookInfoBuilder.title;
        this.description = bookInfoBuilder.description;
        this.price = bookInfoBuilder.price;
        this.url = bookInfoBuilder.url;
        this.library = bookInfoBuilder.library;
        this.author = bookInfoBuilder.author;
        this.tags = bookInfoBuilder.tags;
    }

    public Long getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }


    public String getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public String getLibrary() {
        return library;
    }

    public Set<Tag> getTags() {
        return tags;
    }
}
