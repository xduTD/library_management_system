package server;

import client.Client;
import dao.Book;
import utils.Command;
import utils.NoSuchCommandException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStub implements Client {
    Server server;

    private ServerSocket serverSocket;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;
    private Socket currSocket;

    ServerStub() {
        server = new ServerImpl();
        try {
            serverSocket = new ServerSocket(8189);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerStub stub = new ServerStub();

        while (true) {
            try {
                stub.listenConn();
            } catch (NoSuchCommandException e) {
                System.out.println(e);
            }
        }
    }

    private void listenConn() throws NoSuchCommandException {
        // get connection and I/O stream
        Command command = null;

        // get command
        try {
            currSocket = serverSocket.accept();
            objIn = new ObjectInputStream(currSocket.getInputStream());
            command = (Command) objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // choose server function by command
        switch (command) {
            case ADD_BOOK -> addBook();
            case DELETE_BOOK -> deleteBook();
            case QUERY_BY_ID -> queryByID();
            case QUERY_BY_NAME -> queryByName();
            default -> throw new NoSuchCommandException();
        }
//        if (Command.ADD_BOOK.equals(command)) {
//            addBook();
//        } else if (Command.DELETE_BOOK.equals(command)) {
//            deleteBook();
//        } else if (Command.QUERY_BY_ID.equals(command)) {
//            queryByID();
//        } else if (Command.QUERY_BY_NAME.equals(command)) {
//            queryByName();
//        } else {
//            throw new NoSuchCommandException();
//        }
    }

    /**
     * 四个方法，具体功能包括从流中读取命令的参数，和调用server方法后
     * 通过socket传输返回值给Client端。
     */
    private void addBook() {
        boolean result;

        try {
            result = server.add((Book)objIn.readObject());
            currSocket.shutdownInput();
            System.out.println("addBook() return " + result);
        } catch (IOException | ClassNotFoundException e) {
            result = false;
            e.printStackTrace();
        }
        // 将调用结果通过socket返回给ClientStub
        try {
            objOut = new ObjectOutputStream(currSocket.getOutputStream());
            objOut.writeBoolean(result);
            objOut.flush();
            currSocket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteBook() {
        boolean result;

        try {
            result = server.delete(objIn.readInt());
            currSocket.shutdownInput();
            System.out.println("deleteBook() return " + result);
        } catch (IOException e) {
            result = false;
            e.printStackTrace();
        }
        // 将调用结果通过socket返回给ClientStub
        try {
            objOut = new ObjectOutputStream(currSocket.getOutputStream());
            objOut.writeBoolean(result);
            objOut.flush();
            currSocket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void queryByID() {
        // 查找后返回的书本对象
        Book result;

        try {
            result = server.queryByID(objIn.readInt());
            currSocket.shutdownInput();
            if (result == null) {
                result = new Book(-1, "no such book");
                System.out.println("queryByID()  no such book");
            }
            System.out.println("queryByID() return " + result);
        } catch (IOException e) {
            result = null;
            e.printStackTrace();
        }
        try {
            objOut = new ObjectOutputStream(currSocket.getOutputStream());
            objOut.writeObject(result);
            //objOut.flush();
            currSocket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void queryByName() {
        // 查找后返回的书本对象
        Book result;

        try {
            result = server.queryByName((String)objIn.readObject());
            currSocket.shutdownInput();
            System.out.println("queryByName() return " + result);
        } catch (IOException | ClassNotFoundException e) {
            result = null;
            e.printStackTrace();
        }
        try {
            objOut = new ObjectOutputStream(currSocket.getOutputStream());
            objOut.writeObject(result);
            //objOut.flush();
            currSocket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
