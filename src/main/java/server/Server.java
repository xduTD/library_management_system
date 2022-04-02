package server;

import dao.Book;
import dao.BookList;

import java.io.Serializable;

public interface Server implements Serializable {

    boolean add(Book nb);

    boolean delete(int bookId);

    Book queryByID(int bookId);

    BookList queryByName(String name);

}
