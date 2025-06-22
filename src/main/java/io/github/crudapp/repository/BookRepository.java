package io.github.crudapp.repository;

import io.github.crudapp.model.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();
    Book findById(Long id);
    void save(Book book);
    void deleteById(Long id);
    void deleteAll();

}
