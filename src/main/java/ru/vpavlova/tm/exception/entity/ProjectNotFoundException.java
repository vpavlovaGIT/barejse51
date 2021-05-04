package ru.vpavlova.tm.exception.entity;

import ru.vpavlova.tm.exception.AbstractException;

public class ProjectNotFoundException extends AbstractException {

    public ProjectNotFoundException() {
        super("Error! Project not found...");
    }

}
