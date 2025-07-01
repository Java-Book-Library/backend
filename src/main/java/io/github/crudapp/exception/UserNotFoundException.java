package io.github.crudapp.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
    public UserNotFoundException(String name) {
        super("User with username " + name + " not found");
    }
}
