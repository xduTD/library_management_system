package client;

import dao.Book;
import server.Server;
import utils.BookQueryException;
import utils.NoSuchCommandException;
import utils.ParameterException;

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
    /** 对客户端而言的服务器，实际上是ClientStub */
    private Server server;
    // 如果需要管理员权限，则增加这一行
    // private static Client currentClient;

    public ClientImpl() {
        server = new ClientStub();
    }

    /**
     * 客户端的入口
     * @param args 默认参数
     */
    public static void main(String[] args) {
        ClientImpl client = new ClientImpl();
        // 启动提示语
        System.out.println("================ Library Management System boot... ================");
        // 进入命令行循环读取命令
        client.handleCommand();
    }

    /**
     * 命令行交互方法
     */
    private void handleCommand() {
        String[] operation;
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print(">>");
            operation = scanner.nextLine().split("\\s+", 2);
            try {
                switch (operation[0].toLowerCase()) {
                    case "addbook" -> addBook(operation[1]);
                    case "deletebook", "delbook" -> deleteBook(operation[1]);
                    case "querybook" -> queryBook(operation[1]);
                    case "exit" -> exit();
                    case "options" -> showOptions();
                    default -> throw new NoSuchCommandException();
                }
            } catch (ParameterException | NoSuchCommandException | BookQueryException e) {
                System.out.println(e.getMessage());
            }
        } // end while() {...}
    }

    private void addBook(String parameter) throws ParameterException {
        if (needHelp(parameter.toLowerCase())) {
            System.out.println("addBook [name] [id]");
        } else {
            String[] parameters = parameter.split("\\s+", 2);
            if (parameters.length != 2) {
                throw new ParameterException("require 2 parameters, but " + parameters.length + " found");
            }
            if (!parameters[0].matches("[0-9]+")) {
                throw new ParameterException("invalid parameter for integer [bookID]");
            }
            server.add(new Book(Integer.parseInt(parameters[0]), parameters[1]));
        }
    }

    private void deleteBook(String parameter) throws ParameterException {
        if (needHelp(parameter.toLowerCase())) {
            System.out.println("deleteBook/delBook [bookID]");
        } else {
            if (!parameter.matches("[0-9]+")) {
                throw new ParameterException("invalid parameter for integer [bookID]");
            }
            server.delete(Integer.parseInt(parameter));
        }
    }

    private void queryBook(String parameter) throws ParameterException, BookQueryException {
        Book book;
        if (needHelp(parameter.toLowerCase())) {
            System.out.println("queryBook -n [name]");
            System.out.println("queryBook -id [bookID]");
        } else {
            String[] parameters = parameter.split("\\s+", 2);
            if (parameters.length != 2) {
                throw new ParameterException("require 2 parameters, but " + parameters.length + " found");
            }
            switch (parameters[0].toLowerCase()) {
                case "-n" -> {
                    book = server.queryByName(parameters[1]);
                    if (book.bookID == -1) {
                        throw new BookQueryException("no such book named 《" + parameters[1] + "》");
                    }
                    System.out.println(book.toString());
                }
                case "-id" -> {
                    if (!parameters[1].matches("[0-9]+")) {
                        throw new ParameterException("invalid parameter for integer [bookID]");
                    }
                    book = server.queryByID(Integer.parseInt(parameters[1]));
                    if (book.bookID == -1) {
                        throw new BookQueryException("no such book with id:" + parameters[1]);
                    }
                    System.out.println(book);
                }
                default -> throw new ParameterException("only support '-n/-id' for the first parameter");
            }
        }
    }

    private void showOptions() {
        System.out.println("available commands:");
        System.out.println("    addBook            [id]    [name]");
        System.out.println("    deleteBook/delBook [BookId]");
        System.out.println("    queryBook -n       [bookName]");
        System.out.println("    queryBook -id      [bookId]");
        System.out.println("    options\n");
        // System.out.println("You can also type '-help' after command to review the command structure\n");
    }

    private void exit() {
        System.out.println("==================System Exit!======================");
        System.exit(0);
    }

    /**
     * 是否在命令后面跟着“-help”，如果是则输出该命
     * 令的参数信息及其约束
     ***************************************
     * @param parameter 用户输入的命令中的参数
     * @return isMatch 是否匹配 ’-help‘参数格式
     */
    private boolean needHelp(String parameter) throws ParameterException {
        // 参数是否匹配
        boolean isMatch = parameter.equals("-help");
        // 参数不等于‘-help’，但是又以‘-help’开头，表示有别的参数
        // 跟在-help后面，抛出参数异常
        if (!isMatch && parameter.startsWith("-help")) {
            throw new ParameterException("no character is allowed following '-help'");
        }
        return isMatch;
    }

    /**
     * 大概是这么个实现，等理清再说：
     * 通过error内部定义的方法来实现具体的报错？
     * @param errorMessage 出错信息
     */
    private void error(String errorMessage) {
        System.out.println(errorMessage);
        // System.out.println("You may use command ‘XXX -help’");
    }

}
