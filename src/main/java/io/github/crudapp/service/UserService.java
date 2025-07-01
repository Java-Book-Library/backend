package io.github.crudapp.service;

import io.github.crudapp.model.book.Book;
import io.github.crudapp.model.book.BookDTO;
import io.github.crudapp.model.user.User;
import io.github.crudapp.model.user.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO getUserByName(String name);
    UserDTO addUser(User user);
//    void updateUser(Long id, User update);
//    void deleteUserById(Long id);
//    void deleteAllUsers();

}
