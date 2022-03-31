package client;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;

    public Client() {
        try {
            socket = new Socket("127.0.0.1", 8189);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }


}
