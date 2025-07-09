package io.github.crudapp.service;

import io.github.crudapp.model.book.Book;
import io.github.crudapp.model.user.User;
import io.github.crudapp.model.user.UserDTO;
import io.github.crudapp.model.user_book.BorrowRequest;

import java.util.List;

public interface UserBookService {

    List<Book> getBooksByUserId(Long userId);
    List<UserDTO> getUsersByBookId(Long bookId);
    public boolean isBookBorrowedByUser(Long userId, Long bookId);
    void borrowBook(BorrowRequest request);
    void returnBook(BorrowRequest request);

}
