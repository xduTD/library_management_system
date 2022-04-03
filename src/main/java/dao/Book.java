package dao;

import java.io.Serializable;

public class Book implements Serializable {
    public int bookID;
    String name;

    public Book(int bookID, String name) {
        this.name = name;
        this.bookID = bookID;
    }

    @Override
    public String toString() {
        return "Book: 《" + this.name + "》  id: " + this.bookID;
    }
}
