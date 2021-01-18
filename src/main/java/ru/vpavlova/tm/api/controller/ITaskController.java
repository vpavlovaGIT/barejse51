package ru.vpavlova.tm.api.controller;

public interface ITaskController {

    void showList();

    void create();

    void clear();

    void showTaskById();

    void showTaskByIndex();

    void showTaskByName();

    void removeTaskById();

    void removeTaskByIndex();

    void removeTaskByName();

    void updateTaskById();

    void updateTaskByIndex();

}
