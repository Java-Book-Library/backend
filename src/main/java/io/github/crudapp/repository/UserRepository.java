package io.github.crudapp.repository;

import io.github.crudapp.model.book.Book;
import io.github.crudapp.model.book.BookDTO;
import io.github.crudapp.model.user.User;
import io.github.crudapp.model.user.UserDTO;

import java.util.List;

public interface UserRepository {

    List<UserDTO> findAll();
    UserDTO findByName(String name);
    UserDTO save(User user);
    void update(Long id, User update);
    void deleteById(Long id);
    void deleteAll();

}
