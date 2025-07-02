package io.github.crudapp.service;

import io.github.crudapp.exception.BookNotFoundException;
import io.github.crudapp.exception.InvalidBookException;
import io.github.crudapp.model.book.Book;
import io.github.crudapp.model.user.User;
import io.github.crudapp.model.user.UserDTO;
import io.github.crudapp.model.user_book.BorrowRequest;
import io.github.crudapp.repository.UserBookRepository;
import io.github.crudapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBookServiceImpl implements UserBookService {
    private final UserBookRepository repo;

    public UserBookServiceImpl(UserBookRepository repo) {
        this.repo = repo;
    }

    public List<Book> getBooksByUserId(Long userId) {
        List<Book> books = repo.findBooksByUserId(userId);
        if (books.isEmpty()) {
            throw new BookNotFoundException(userId);
        }
        return books;
    }

    public List<UserDTO> getUsersByBookId(Long bookId) {
        List<UserDTO> users = repo.findUsersByBookId(bookId);
        if (users.isEmpty()) {
            throw new BookNotFoundException(bookId);
        }
        return users;
    }

    public void borrowBook(BorrowRequest request) {
        repo.borrowBook(request.getUserId(), request.getBookId());
    }

    public void returnBook(BorrowRequest request) {
        repo.returnBook(request.getUserId(), request.getBookId());
    }

}
