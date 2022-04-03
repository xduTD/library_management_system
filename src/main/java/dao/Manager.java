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
        int bookNumber = books.size();
        /* 删除成功标记 */
        operationFlag = false;
        for (int i = 0; i < bookNumber; i++) {
            if (books.get(i).bookID == bookID) {
                books.remove(books.get(i));
                operationFlag = true;
            }
        }
    }

    /**
     *
     */
    public Book queryByID(int bookID) {
        operationFlag = false;
        Book target = null;
        for (Book book : books) {
            if (book.bookID == bookID) {
                target = book;
                operationFlag = true;
            }
        }

        return target;
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

