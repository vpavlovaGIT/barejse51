package ru.vpavlova.tm.exception.entity;

import ru.vpavlova.tm.exception.AbstractException;

public class ObjectNotFoundException extends AbstractException {

    public ObjectNotFoundException() {
        super("Error! Object not found...");
    }

}
