package ru.vpavlova.tm.exception.system;

import ru.vpavlova.tm.exception.AbstractException;

public class UnknownCommandException extends AbstractException {

    public UnknownCommandException() {
        super("Error! Command not found...");
    }

}
