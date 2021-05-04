package ru.vpavlova.tm.exception.empty;

import ru.vpavlova.tm.exception.AbstractException;

public class EmptyUserIdException extends AbstractException {

    public EmptyUserIdException() {
        super("Error! User Id not found...");
    }

}
