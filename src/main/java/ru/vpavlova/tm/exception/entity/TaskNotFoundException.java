package ru.vpavlova.tm.exception.entity;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        super("Error! Task not found...");
    }

}
