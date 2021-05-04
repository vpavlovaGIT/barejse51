package ru.vpavlova.tm.exception.empty;

import ru.vpavlova.tm.exception.AbstractException;

public class EmptyIdException extends AbstractException {

    public EmptyIdException() {
        super("Error! Id is empty...");
    }

}
