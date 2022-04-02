package dao;

import java.util.ArrayList;

public class Manager {
    /* singleton */
    public static Manager manager = null;
    /* 书架 */
    private ArrayList<Book> books = new ArrayList<>();

    private Manager() { }

    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
        }
        return manager;
    }

    /* */
    public void addBook(Book book) {
        books.add(book);
    }

    /* */
    public void deleteBook(int bookID) {
        /* 删除成功标记 */
        boolean deleteFlag = false;
        for (Book book : books) {
            if (book.bookId == bookID) {
                books.remove(book);
                deleteFlag = true;
            }
        }
        if (!deleteFlag) {
            System.out.println("No such book!");
        }
    }

    /**/
    public Book queryByID(int bookID) {
        boolean queryFlag = false;
        Book ans = null;
        for (Book book : books) {
            if (book.bookId == bookID) {
                ans = book;
                queryFlag = true;
            }
        }
        if (!queryFlag) {
            System.out.println("No such book!");
            return null;
        }
        return ans;
    }

    /**/
    public Book queryByName(String bookName) {
        boolean queryFlag = false;
        Book ans = null;
        for (Book book : books) {
            if (book.name == bookName) {
                ans = book;
                queryFlag = true;
            }
        }
        if (!queryFlag) {
            System.out.println("No such book!");
            return null;
        }
        return ans;
    }



}

