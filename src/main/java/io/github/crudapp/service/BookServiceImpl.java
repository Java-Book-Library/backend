package io.github.crudapp.service;

import io.github.crudapp.exception.BookNotFoundException;
import io.github.crudapp.exception.InvalidBookException;
import io.github.crudapp.model.book.AbstractBook;
import io.github.crudapp.model.book.Book;
import io.github.crudapp.model.book.BookDTO;
import io.github.crudapp.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repo;

    public BookServiceImpl(BookRepository repo) {
        this.repo = repo;
    }

    private void validate(AbstractBook book) {
        if (book == null) {
            throw new BookNotFoundException("Book cannot be null");
        }
        if (book.getTitle() == null || book.getTitle().isBlank()) {
            throw new InvalidBookException("Title cannot be empty");
        }
        if (book.getPrice() == null || book.getPrice() < 0) {
            throw new InvalidBookException("Price cannot be negative");
        }
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public Book getBookById(Long id) {
        Book book = repo.findById(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        return book;
    }

    public Book addBook(BookDTO book) {
        validate(book);
        Book savedBook = repo.save(book);
        if (savedBook == null) {
            throw new InvalidBookException("Failed to save book");
        }
        if (savedBook.getId() == null) {
            throw new InvalidBookException("Saved book has no id");
        }
        return savedBook;
    }

    public void updateBook(Long id, BookDTO update) {
        if (repo.findById(id) == null) {
            throw new BookNotFoundException(id);
        }
        validate(update);
        repo.update(id, update);
    }

    public void deleteBookById(Long id) {
        if (repo.findById(id) == null) {
            throw new BookNotFoundException(id);
        }
        repo.deleteById(id);
    }

    public void deleteAllBooks() {
        repo.deleteAll();
    }

}
