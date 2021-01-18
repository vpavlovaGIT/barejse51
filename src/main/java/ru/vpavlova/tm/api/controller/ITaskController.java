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

    void startProjectById();

    void startProjectByIndex();

    void startProjectByName();

    void finishProjectById();

    void finishProjectByIndex();

    void finishProjectByName();

    void changeProjectStatusById();

    void changeProjectStatusByIndex();

    void changeProjectStatusByName();

}
