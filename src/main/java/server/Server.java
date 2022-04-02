package server;

import dao.Book;

public interface Server {

    boolean add(Book nb);

    boolean delete(int bookId);

    Book queryByID(int bookId);

    Book queryByName(String name);

}
