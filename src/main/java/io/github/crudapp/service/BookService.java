package io.github.crudapp.service;

import io.github.crudapp.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    void addBook(Book book);
    /*
    Book getBookById(Long id);
    void deleteBook(Book book);
    */

}
