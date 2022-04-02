package client;

import client.error.ErrorType;
import server.Server;

import java.io.IOException;

/**
 * command line :
 *     addbook [bookName bookId [opt]number]
 *     delbook [bookName bookId]
 *     query -n [book name]
 *     query -id [book id]
 *     options
 *     exit
 *     XXX -help
 ****************************************
 * define function ‘void error(String errorMessage)’ when met error command
 *
 */

public class ClientImpl implements Client {
    /* 对客户端而言的服务器，实际上是ClientStub */
    private Server server;
    // private Client currentClient;

    public ClientImpl() {
        server = new ClientStub();

    }

    /**
     * @descrption
     * @param args
     */
    public static void main(String[] args) {
        Client client = new ClientImpl();


    }

    /**
     * 大概是这么个实现，等理清再说：
     * 通过error内部定义的方法来实现具体的报错？
     * @param errorMessage 出错信息
     */
    public void error(String errorMessage) {
        System.out.println(errorMessage);
        // System.out.println("You may use command ‘XXX -help’");
    }

    public void error(String errorMessage, ErrorType error) {
        System.out.println(errorMessage);
        System.out.println("You may use command ‘XXX -help’");
    }

}
