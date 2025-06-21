package io.github.crudapp.service;

import io.github.crudapp.model.Book;
import io.github.crudapp.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repo;

    public BookServiceImpl(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public void addBook(Book book) {
        repo.save(book);
    }

}
