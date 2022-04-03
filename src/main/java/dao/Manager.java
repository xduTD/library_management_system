package dao;

import java.util.ArrayList;

public class Manager {
    /** singleton */
    public static Manager manager;
    /** 书架 */
    private ArrayList<Book> books;
    /** 操作是否成功 */
    private boolean operationFlag;

    /** constructor */
    private Manager() {
        books = new ArrayList<>();
    }

    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
        }
        return manager;
    }

    /**
     *
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     *
     */
    public void deleteBook(int bookID) {
        /* 删除成功标记 */
        operationFlag = false;
        for (Book book : books) {
            if (book.bookID == bookID) {
                books.remove(book);
                operationFlag = true;
            }
        }
    }

    /**
     *
     */
    public Book queryByID(int bookID) {
        boolean queryFlag = false;
        Book ans = null;
        for (Book book : books) {
            if (book.bookID == bookID) {
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

    /**
     *
     */
    public Book queryByName(String bookName) {
        operationFlag = false;
        Book target = null;
        for (Book book : books) {
            if (book.name == bookName) {
                target = book;
                operationFlag = true;
            }
        }
        return target;
    }

    /**
     *
     */
    public boolean getOperationFlag() {
        return operationFlag;
    }

}

