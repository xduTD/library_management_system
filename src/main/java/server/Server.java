package server;

import dao.Book;
import dao.BookList;

public interface Server {

    boolean add(Book nb);

    boolean delete(int bookId);

    Book queryByID(int bookId);

    BookList queryByName(String name);

}
