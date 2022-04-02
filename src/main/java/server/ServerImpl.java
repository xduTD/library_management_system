package server;

import dao.Book;
import dao.Manager;

public class ServerImpl implements Server {
    private Manager manager;

    public ServerImpl() {

        this.manager = Manager.getInstance();
    }

    public boolean add(Book nb){
        manager.addBook(nb);
        return true;
    }

    public boolean delete(int bookId){
        manager.deleteBook(bookId);
        return true;
    }

    public Book queryByID(int bookId){
        return manager.queryByID(bookId);
    }

    public Book queryByName(String name){
        return manager.queryByName(name);
    }

}