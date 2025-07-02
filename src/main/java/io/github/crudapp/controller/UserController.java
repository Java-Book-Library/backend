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

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String name) {
        UserDTO user = service.getUserByName(name);
        return ResponseEntity.ok(user); // 200 OK
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
        UserDTO savedUser = service.addUser(user);
        URI location = URI.create("/api/users/" + savedUser.getId());
        return ResponseEntity.created(location).body(savedUser); // 201 Created
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> authenticateUser(@RequestBody User user) {
        UserDTO foundUser = service.authenticateUser(user.getName(), user.getPassword());
        URI location = URI.create("/api/users/" + foundUser.getId());
        return ResponseEntity.created(location).body(foundUser); // 201 Created
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

}
