package io.github.crudapp.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String name) {
        super("User with username " + name + " already exists");
    }

}