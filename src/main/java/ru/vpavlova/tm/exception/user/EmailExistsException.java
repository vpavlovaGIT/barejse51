package ru.vpavlova.tm.exception.user;

import ru.vpavlova.tm.exception.AbstractException;

public class EmailExistsException extends AbstractException {

    public EmailExistsException(String message) {
        super("Error! Email already exists...");
    }

}