package ru.vpavlova.tm.exception.user;

import ru.vpavlova.tm.exception.AbstractException;

public class AccessDeniedException extends AbstractException {

    public AccessDeniedException() {
        super("Error! Access denied exception...");
    }

}