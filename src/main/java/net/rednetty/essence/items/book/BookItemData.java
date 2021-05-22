package net.rednetty.essence.items.book;

import net.rednetty.essence.items.interact.InteractItemData;

import java.util.List;

/**
 * Created by Giovanni on 2/11/2021
 */
public class BookItemData extends InteractItemData {

    private String author;
    private List<BookPage> pages;

    public List<BookPage> getPages() {
        return pages;
    }

    public void setPages(List<BookPage> pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
