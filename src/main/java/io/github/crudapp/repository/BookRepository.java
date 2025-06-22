package io.github.crudapp.repository;

import io.github.crudapp.model.Book;
import io.github.crudapp.model.BookDTO;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();
    Book findById(Long id);
    Book save(BookDTO book);
    void update(Long id, BookDTO update);
    void deleteById(Long id);
    void deleteAll();

}
