package ru.vpavlova.tm.exception.empty;

import ru.vpavlova.tm.exception.AbstractException;

public class EmptyNameException extends AbstractException {

    public EmptyNameException() {
        super("Error! Name is empty...");
    }

}
