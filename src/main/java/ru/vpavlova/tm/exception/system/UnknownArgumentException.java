package ru.vpavlova.tm.exception.system;

import ru.vpavlova.tm.exception.AbstractException;

public class UnknownArgumentException extends AbstractException {

    public UnknownArgumentException(String arg) {
        super("Error! Argument ``" + arg + "`` not found...");
    }

}
