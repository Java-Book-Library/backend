package io.github.crudapp.repository;

import io.github.crudapp.model.user.User;
import io.github.crudapp.model.user.UserDTO;

import java.util.List;

public interface UserRepository {

    UserDTO findByName(String name);
    UserDTO save(User user);
    String getPassword(String name);
//    void update(Long id, User update);
//    void deleteById(Long id);

}
