package io.github.crudapp.service;

import io.github.crudapp.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    Book getBookById(Long id);
    void addBook(Book book);
    void deleteBookById(Long id);
    void deleteAllBooks();

}
