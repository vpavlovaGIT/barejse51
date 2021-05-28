package ru.vpavlova.tm.exception.empty;

import ru.vpavlova.tm.exception.AbstractException;

public class EmptyDescriptionException extends AbstractException {

    public EmptyDescriptionException() {
        super("Error! Description is empty...");
    }

}
