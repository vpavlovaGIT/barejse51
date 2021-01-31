package ru.vpavlova.tm.exception.system;

import ru.vpavlova.tm.exception.AbstractException;

public class UnknownCommandException extends AbstractException {

    public UnknownCommandException(String command) {
        super("Error! Command ``" + command + "`` not found...");
    }

}
