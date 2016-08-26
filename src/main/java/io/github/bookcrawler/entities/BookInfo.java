package io.github.bookcrawler.entities;

public class BookInfo {


    String title;


    public BookInfo(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
