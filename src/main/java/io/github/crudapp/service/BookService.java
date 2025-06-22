package io.github.crudapp.service;

import io.github.crudapp.model.Book;
import io.github.crudapp.model.BookDTO;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    Book getBookById(Long id);
    void addBook(Book book);
    void updateBook(Long id, BookDTO update);
    void deleteBookById(Long id);
    void deleteAllBooks();

}
