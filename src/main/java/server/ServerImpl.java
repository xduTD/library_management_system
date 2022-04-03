package server;

import dao.Book;
import dao.Manager;

public class ServerImpl implements Server {
    private Manager manager;

    public ServerImpl() {
        this.manager = Manager.getInstance();
    }

    public boolean add(Book book){
        manager.addBook(book);
        return manager.getOperationFlag();
    }

    public boolean delete(int bookID){
        manager.deleteBook(bookID);
        return manager.getOperationFlag();
    }

    public Book queryByID(int bookID){
        return manager.queryByID(bookID);
    }

    public Book queryByName(String name){
        return manager.queryByName(name);
    }

}