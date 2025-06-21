package io.github.crudapp.repository;

import io.github.crudapp.model.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();
    void save(Book book);
    /*
    Book findById(Long id);
    void delete(Book book);
    */

}
