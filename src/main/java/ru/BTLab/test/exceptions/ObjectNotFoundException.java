package ru.BTLab.test.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {

        super(message);
    }
}
