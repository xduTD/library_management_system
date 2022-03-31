package server;

import dao.Book;
import dao.BookList;
import dao.Manager;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerImpl implements Server {
    private ServerSocket listenSocket;
    private Manager manager;

    public ServerImpl() {

        this.manager = new Manager();
    }

    public static void main(String[] args) {
        ServerImpl server = new ServerImpl();

        try {
            server.listenSocket = new ServerSocket(8189);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.carryout();
    }

    public boolean add(Book nb){

        /*
        b[number] = nb;
        number++;
        if(number >= 101){
            number--;
            return false;
        }
        */
        return false;
    }

    public boolean delete(int bookId){

        return false;
        /*
        int i = 0;
        boolean flag = false;
        for(i = 0; i < 100; i++){
            if(bookId == b[i].bookId ){
                flag = true;
                for(;i < 99;i++){
                    b[i] = b[i + 1];
                }
                b[99].bookId = 0;
                b[99].name = "";
            }

        }
        return flag;

         */
    }

    public Book queryByID(int bookId){

        return null;
        /*
        int i = 0;
        Book bd = new Book(0,"");
        for(i = 0; i < 100; i++){
            if(bookId == b[i].bookId ){
                bd.name = b[i].name;
                bd.bookId = b[i].bookId;
            }
        }
        return bd;
        */
    }

    public BookList queryByName(String name){

        /*
        int i = 0;
        int j = 0;
        BookList ans = new BookList();
        for(i = 0; i < 100; i++){
            if(b[i].name.indexOf(name) != -1){
                ans.b[j] = b[i];
                System.out.println("Book name :"+ b[i].name +"|"+"Book ID :"+b[i].bookId);
                j++;
            }
        }
        return ans;

         */
        return null;
    }




    private void carryout() {

    }

    /*
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServerImpl server = new ServerImpl();
        server.carryout(scanner);

    }

    private void carryout(Scanner scanner) {
        System.out.println("----------------------System Boot---------------------------");
        System.out.println("1.create book");
        System.out.println("2.add book");
        System.out.println("3.delete book");
        System.out.println("4.find book by ID");
        System.out.println("5.find book by name");
        System.out.println("-------------------------------------------------");

        while (true) {
            System.out.print("Your command: ");
            String command = scanner.nextLine() ;
            switch (command) {
                case "1" :
                    createBook();
                    break;
                case "2" :
                    addBook();
                    break;
                case "3" :
                    deleteBook();
                    break;
                case "4" :
                    findBookById();
                    break;
                case "5" :
                    findBookByName();
                    break;
                default:
            }
        }
    }

    private void findBookByName() {

    }

    private void findBookById() {

    }

    private void deleteBook() {
    }

    private void addBook() {
    }

    private void createBook() {
    }
    */

}
