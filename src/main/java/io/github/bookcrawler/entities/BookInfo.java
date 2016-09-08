package io.github.bookcrawler.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
public class BookInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private Long ID;

    private String title;

    @Column(length = 10000)
    private String description;

    private String price;

    private String library;

    private String url;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private Date inputDate;

    @ManyToMany
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
        this.inputDate = bookInfoBuilder.inputDate;
        this.tags = bookInfoBuilder.tags;
    }

    public Long getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author.getName();
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

    public Date getInputDate() {
        return inputDate;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", library='" + library + '\'' +
                ", url='" + url + '\'' +
                ", author=" + author +
                ", inputDate=" + inputDate +
                ", tags=" + tags +
                '}';
    }
}
