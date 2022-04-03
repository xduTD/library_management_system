package client;

import dao.Book;
import server.Server;
import utils.Command;

import java.io.*;
import java.net.Socket;

public class ClientStub implements Server {
    private static boolean initFlag = false;
    private Socket socket;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;

    /* Constructor */
    public ClientStub() {}

    /** interfaces here */
    @Override
    public boolean add(Book book) {
        init();
        boolean result = true;
        try {
            objOut.writeObject(Command.ADD_BOOK);
            objOut.flush();
            objOut.writeObject(book);
            objOut.flush();
            socket.shutdownOutput();
            objIn = new ObjectInputStream(socket.getInputStream());
            result = objIn.readBoolean();
            socket.shutdownInput();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(int bookID) {
        init();

        boolean result = true;
        try {
            objOut.writeObject(Command.DELETE_BOOK);
            objOut.flush();
            objOut.writeInt(bookID);
            objOut.flush();
            socket.shutdownOutput();
            objIn = new ObjectInputStream(socket.getInputStream());
            result = objIn.readBoolean();
            socket.shutdownInput();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Book queryByID(int bookID) {
        init();

        Book result = null;
        try {
            objOut.writeObject(Command.QUERY_BY_ID);
            objOut.flush();
            objOut.writeInt(bookID);
            objOut.flush();
            socket.shutdownOutput();
            objIn = new ObjectInputStream(socket.getInputStream());
            result = (Book)objIn.readObject();
            socket.shutdownInput();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Book queryByName(String bookName) {
        init();

        Book result = null;
        try {
            objOut.writeObject(Command.QUERY_BY_NAME);
            objOut.flush();
            objOut.writeObject(bookName);
            objOut.flush();
            socket.shutdownOutput();
            objIn = new ObjectInputStream(socket.getInputStream());
            result = (Book)objIn.readObject();
            socket.shutdownInput();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * socket only init once
     */
    private void init() {
        if (!initFlag) {
            try {
                socket = new Socket("127.0.0.1", 8189);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            objOut = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
