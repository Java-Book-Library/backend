package io.github.crudapp.repository;

import io.github.crudapp.model.book.Book;
import io.github.crudapp.model.book.BookDTO;
import io.github.crudapp.model.user.User;
import io.github.crudapp.model.user.UserDTO;

import java.util.List;

public interface UserBookRepository {

    List<Book> findBooksByUserId(Long userId);
    List<UserDTO> findUsersByBookId(Long bookId);
    void borrowBook(Long userId, Long bookId);
    void returnBook(Long userId, Long bookId);
    boolean isBookBorrowedByUser(Long userId, Long bookId);

}
