package io.github.crudapp.controller;

import io.github.crudapp.model.Book;
import io.github.crudapp.model.BookDTO;
import io.github.crudapp.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        List<Book> books = service.getAllBooks();
        return ResponseEntity.ok(books); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = service.getBookById(id);
        return ResponseEntity.ok(book); // 200 OK
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        service.addBook(book);
        URI location = URI.create("/api/books/" + book.getId());
        return ResponseEntity.created(location).body(book); // 201 Created
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long id, @RequestBody BookDTO update) {
        service.updateBook(id, update);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        service.deleteBookById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllBooks() {
        service.deleteAllBooks();
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
