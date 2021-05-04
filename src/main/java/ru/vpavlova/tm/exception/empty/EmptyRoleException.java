package ru.vpavlova.tm.exception.empty;

import ru.vpavlova.tm.exception.AbstractException;

public class EmptyRoleException extends AbstractException {

    public EmptyRoleException() {
        super("Error! User role is empty...");
    }

}
