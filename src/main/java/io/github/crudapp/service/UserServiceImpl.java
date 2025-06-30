package io.github.crudapp.service;

import io.github.crudapp.exception.BookNotFoundException;
import io.github.crudapp.exception.InvalidBookException;
import io.github.crudapp.model.book.AbstractBook;
import io.github.crudapp.model.book.Book;
import io.github.crudapp.model.book.BookDTO;
import io.github.crudapp.model.user.User;
import io.github.crudapp.model.user.UserDTO;
import io.github.crudapp.repository.BookRepository;
import io.github.crudapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    private void validate(User user) {
        if (user == null) {
            throw new BookNotFoundException("User cannot be null");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            throw new InvalidBookException("Username cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new InvalidBookException("Password cannot be empty");
        }
    }

    public List<UserDTO> getAllUsers() {
        return repo.findAll();
    }

    public UserDTO getUserById(Long id) {
        UserDTO user = repo.findById(id);
        if (user == null) {
            throw new BookNotFoundException(id);
        }
        return user;
    }

    public UserDTO addUser(User user) {
        validate(user);
        UserDTO savedUser = repo.save(user);
        if (savedUser == null) {
            throw new InvalidBookException("Failed to save user");
        }
        if (savedUser.getId() == null) {
            throw new InvalidBookException("Saved user has no id");
        }
        return savedUser;
    }

    public void updateUser(Long id, User update) {
        if (repo.findById(id) == null) {
            throw new BookNotFoundException(id);
        }
        validate(update);
        repo.update(id, update);
    }

    public void deleteUserById(Long id) {
        if (repo.findById(id) == null) {
            throw new BookNotFoundException(id);
        }
        repo.deleteById(id);
    }

    public void deleteAllUsers() {
        repo.deleteAll();
    }

}
