package io.github.crudapp.service;

import io.github.crudapp.model.book.Book;
import io.github.crudapp.model.book.BookDTO;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book addBook(BookDTO book);
    void updateBook(Long id, BookDTO update);
    void deleteBookById(Long id);
    void deleteAllBooks();

}
