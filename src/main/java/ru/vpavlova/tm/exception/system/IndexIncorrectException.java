package ru.vpavlova.tm.exception.system;

import ru.vpavlova.tm.exception.AbstractException;

public class IndexIncorrectException extends AbstractException {

    public IndexIncorrectException(final String value) {
        super("Error! This value ``" + value + "`` is not number...");
    }

    public IndexIncorrectException() {
        super("Error! Index is incorrect...");
    }

}
