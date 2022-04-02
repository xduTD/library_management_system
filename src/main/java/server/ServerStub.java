package server;

import client.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStub implements Client {
    ServerStub stub;

    private ServerSocket serverSocket;
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;
    private Socket currSocket;

    ServerStub() {
        try {
            serverSocket = new ServerSocket(8189);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerStub stub = new ServerStub();

        while (true) {
            stub.listenConn();
        }
    }

    private void listenConn() {
        /* get connection & I/O stream */
        try {
            currSocket = serverSocket.accept();
            in = new BufferedInputStream(currSocket.getInputStream());
            out = new BufferedOutputStream(currSocket.getOutputStream());
            objIn = new ObjectInputStream(in);
            objOut = new ObjectOutputStream(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        in.

    }

}
