package io.github.crudapp.controller;

import io.github.crudapp.model.user.User;
import io.github.crudapp.model.user.UserDTO;
import io.github.crudapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = service.getAllUsers();
        return ResponseEntity.ok(users); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String name) {
        UserDTO user = service.getUserByName(name);
        return ResponseEntity.ok(user); // 200 OK
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody User book) {
        UserDTO savedUser = service.addUser(book);
        URI location = URI.create("/api/books/" + savedUser.getId());
        return ResponseEntity.created(location).body(savedUser); // 201 Created
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody User update) {
//        service.updateUser(id, update);
//        return ResponseEntity.noContent().build(); // 204 No Content
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
//        service.deleteUserById(id);
//        return ResponseEntity.noContent().build(); // 204 No Content
//    }
//
//    @DeleteMapping
//    public ResponseEntity<Void> deleteAllUsers() {
//        service.deleteAllUsers();
//        return ResponseEntity.noContent().build(); // 204 No Content
//    }

}
