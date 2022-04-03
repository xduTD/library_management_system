package server;

import dao.Book;

public interface Server {

    boolean add(Book book);

    boolean delete(int bookID);

    Book queryByID(int bookID);

    Book queryByName(String name);

}
