package ru.vpavlova.tm.exception.empty;

import ru.vpavlova.tm.exception.AbstractException;

public class EmptyPasswordException extends AbstractException {

    public EmptyPasswordException() {
        super("Error! Password is empty...");
    }

}