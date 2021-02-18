package ru.vpavlova.tm.exception.empty;

import ru.vpavlova.tm.exception.AbstractException;

public class EmptyLoginException extends AbstractException {

    public EmptyLoginException() {
        super("Error! Login is empty...");
    }

}
