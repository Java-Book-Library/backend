package io.github.crudapp.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Book with id " + id + " not found");
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}