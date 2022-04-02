package client;

import dao.Book;
import server.Server;
import utils.Command;

import java.io.*;
import java.net.Socket;

public class ClientStub implements Server {
    private Socket socket;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;

    /* Constructor */
    public ClientStub() {
        try {
            socket = new Socket("127.0.0.1", 8189);
            objIn = new ObjectInputStream(socket.getInputStream());
            objOut = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** interfaces here */
    @Override
    public boolean add(Book book) {
        boolean result = true;
        try {
            objOut.writeObject(Command.ADD_BOOK);
            objOut.flush();
            objOut.writeObject(book);
            objOut.flush();
            result = objIn.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(int bookID) {
        boolean result = true;
        try {
            objOut.writeObject(Command.DELETE_BOOK);
            objOut.flush();
            objOut.writeInt(bookID);
            objOut.flush();
            result = objIn.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Book queryByID(int bookID) {
        Book result = null;
        try {
            objOut.writeObject(Command.QUERY_BY_ID);
            objOut.flush();
            objOut.writeInt(bookID);
            objOut.flush();
            result = (Book)objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Book queryByName(String bookName) {
        Book result = null;
        try {
            objOut.writeObject(Command.QUERY_BY_NAME);
            objOut.flush();
            objOut.writeObject(bookName);
            objOut.flush();
            result = (Book)objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

}
