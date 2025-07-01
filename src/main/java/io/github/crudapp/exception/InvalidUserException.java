package io.github.crudapp.exception;

public class InvalidUserException extends RuntimeException{

    public InvalidUserException(String message) {
        super(message);
    }
    
}
