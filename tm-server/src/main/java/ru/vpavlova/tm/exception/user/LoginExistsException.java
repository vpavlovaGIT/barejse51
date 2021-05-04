package ru.vpavlova.tm.exception.user;

import ru.vpavlova.tm.exception.AbstractException;

public class LoginExistsException extends AbstractException {

    public LoginExistsException() {
        super("Error! Login already exists...");
    }

}
