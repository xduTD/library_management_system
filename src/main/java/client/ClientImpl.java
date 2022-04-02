package client;

import dao.Book;
import server.Server;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

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
     *
     * @param args
     */
    public static void main(String[] args) {
        ClientImpl client = new ClientImpl();

        // 进入命令行循环读取命令
        client.handleCommand();
    }

    private void handleCommand() {
        String userInput;
        int bookID = 0;
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        String[] c1 = null;
        System.out.println("here");

        while(flag){
            System.out.println("your command >>");
            userInput = scanner.nextLine();
            c1 = userInput.split(" ");
            if(c1[0].equalsIgnoreCase("addBook")){
                bookID = Integer.parseInt(c1[2]);
                Book nb = new Book(bookID,c1[1]);
                server.add(nb);
                continue;
            }
            else if(c1[0].equalsIgnoreCase("queryBook")){
                if(c1[1].equalsIgnoreCase("name")){
                    System.out.println("find book "+c1[2]+"successfully!");
                    server.queryByName(c1[2]);
                    continue;
                }
                else if(c1[1].equalsIgnoreCase("id")){
                    bookID = Integer.parseInt(c1[2]);
                    server.queryByID(bookID);
                    continue;
                }
                else{
                    continue;
                }
            }
            else if(c1[0].equalsIgnoreCase("deleteBook")){
                bookID = Integer.parseInt(c1[2]);
                server.delete(bookID);
                continue;
            }
            else if(c1[0].equalsIgnoreCase("exit")){
                System.out.println("Quit system!");
                break;
            }
            else if(c1[0].equalsIgnoreCase("-help")){
                System.out.println("-------------Command Instruction Format----------------");
                System.out.println("createBook [name] [id]");
                System.out.println("addBook [name] [id]");
                System.out.println("queryBook name [bookName]");
                System.out.println("queryBook id [bookId]");
                System.out.println("deleteBook [BookId]");
                continue;
            }
            else{
                System.out.println("useless command");
                continue;
            }
        }
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

}
