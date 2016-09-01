package io.github.bookcrawler.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long ID;

    private String name;

    private Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (name == null || author.name == null) return false;
        return name.trim().equals(author.name.trim());
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        return 31 * result;
    }
}
