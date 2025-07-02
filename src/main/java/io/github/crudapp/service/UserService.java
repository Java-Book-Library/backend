package io.github.crudapp.service;

import io.github.crudapp.model.user.User;
import io.github.crudapp.model.user.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUserByName(String name);
    UserDTO addUser(User user);
    UserDTO authenticateUser(String name, String password);
//    void updateUser(Long id, User update);
//    void deleteUserById(Long id);

}
