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
    void addBook(Book book) {
        books.add(book);
    }

    /* */
    void deleteBook(int bookID) {
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
    Book queryByID(int bookID) {
        return null;

    }

    /**/
    Book queryByName(String bookName) {
        return null;
    }



}

