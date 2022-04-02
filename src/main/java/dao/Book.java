package dao;

import java.io.Serializable;

public class Book implements Serializable{
    int bookId;
    String name;
    public Book(int bookID, String name){
        this.name = name;
        this.bookId = bookID;
    }
}
