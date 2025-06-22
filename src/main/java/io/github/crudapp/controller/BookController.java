package io.github.crudapp.controller;

import io.github.crudapp.model.Book;
import io.github.crudapp.model.BookDTO;
import io.github.crudapp.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getAll() {
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return service.getBookById(id);
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        service.addBook(book);
    }

    @PatchMapping("/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody BookDTO update) {
        service.updateBook(id, update);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        service.deleteBookById(id);
    }

    @DeleteMapping
    public void deleteAllBooks() {
        service.deleteAllBooks();
    }

}
