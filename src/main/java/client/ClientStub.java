package client;

import dao.Book;
import server.Server;

import java.io.IOException;
import java.net.Socket;

public class ClientStub implements Server {
    private Socket socket;

    /* Constructor */
    public ClientStub() {
        try {
            socket = new Socket("127.0.0.1", 8189);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** interfaces here */
    @Override
    public boolean add(Book book) {
        return false;
    }

    @Override
    public boolean delete(int bookId) {
        return false;
    }

    @Override
    public Book queryByID(int bookId) {
        return null;
    }

    @Override
    public Book queryByName(String bookName) {
        return null;
    }

    /** transport function */

}
