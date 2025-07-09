package io.github.crudapp.controller;

import io.github.crudapp.model.book.Book;
import io.github.crudapp.model.user.UserDTO;
import io.github.crudapp.model.user_book.BorrowRequest;
import io.github.crudapp.service.UserBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-book")
public class UserBookController {
    private final UserBookService service;

    public UserBookController(UserBookService service) {
        this.service = service;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Book>> getBooksByUserId(@PathVariable Long userId) {
        List<Book> users = service.getBooksByUserId(userId);
        return ResponseEntity.ok(users); // 200 OK
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<UserDTO>> getUsersByBookId(@PathVariable Long bookId) {
        List<UserDTO> user = service.getUsersByBookId(bookId);
        return ResponseEntity.ok(user); // 200 OK
    }

    @GetMapping("/book/is-borrowed")
    public ResponseEntity<Boolean> isBookBorrowedByUser(
            @RequestParam Long userId,
            @RequestParam Long bookId
    ) {
        boolean isBorrowed = service.isBookBorrowedByUser(userId, bookId);
        return ResponseEntity.ok(isBorrowed); // 200 OK
    }

    @PostMapping("/borrow")
    public ResponseEntity<Void> borrowBook(@RequestBody BorrowRequest request) {
        service.borrowBook(request);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PostMapping("/return")
    public ResponseEntity<Void> returnBook(@RequestBody BorrowRequest request) {
        service.returnBook(request);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
