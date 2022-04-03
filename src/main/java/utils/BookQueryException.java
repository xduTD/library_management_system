package utils;

public class BookQueryException extends Exception {

    public BookQueryException(String message) {
        super("Query Failed: " + message);
    }
}
