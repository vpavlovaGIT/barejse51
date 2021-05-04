package ru.vpavlova.tm.exception.entity;

import ru.vpavlova.tm.exception.AbstractException;

public class UserNotFoundException extends AbstractException {

    public UserNotFoundException() {
        super("Error! User not found...");
    }

}
