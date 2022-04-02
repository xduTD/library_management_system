package client;

import dao.Book;
import server.Server;

import java.io.*;
import java.net.Socket;

public class ClientStub implements Server {
    private Socket socket;
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;

    /* Constructor */
    public ClientStub() {
        try {
            socket = new Socket("127.0.0.1", 8189);
            in = new BufferedInputStream(socket.getInputStream());
            out = new BufferedOutputStream(socket.getOutputStream());
            objIn = new ObjectInputStream(in);
            objOut = new ObjectOutputStream(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** interfaces here */
    @Override
    public boolean add(Book book) {
        boolean result = true;
        try {
            out.write(1);
            out.flush();
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
            out.write(2);
            out.flush();
            out.write(bookID);
            out.flush();
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
            out.write(3);
            out.flush();
            out.write(bookID);
            out.flush();
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
            out.write(4);
            out.flush();
            new PrintWriter(out).println(bookName);
            result = (Book)objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

}
