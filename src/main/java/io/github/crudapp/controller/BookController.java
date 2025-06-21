package io.github.crudapp.controller;

import io.github.crudapp.model.Book;
import io.github.crudapp.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public void addBook(@RequestBody Book book) {
        service.addBook(book);
    }

}
