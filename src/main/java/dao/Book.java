package dao;

import java.io.Serializable;

public class Book implements {
    int bookId;
    String name;
    public Book(int bookID, String name){
        this.name = name;
        this.bookId = bookID;
    }
}
