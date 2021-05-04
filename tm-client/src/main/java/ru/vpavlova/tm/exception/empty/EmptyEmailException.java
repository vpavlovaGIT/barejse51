package ru.vpavlova.tm.exception.empty;

import ru.vpavlova.tm.exception.AbstractException;

public class EmptyEmailException extends AbstractException {

    public EmptyEmailException() {
        super("Error! Email is empty...");
    }

}
