package ru.vpavlova.tm.api.controller;

public interface IProjectController {

    void showProjectList();

    void createProject();

    void clearProject();

    void showProjectById();

    void showProjectByIndex();

    void showProjectByName();

    void removeProjectById();

    void removeProjectByIndex();

    void removeProjectByName();

    void updateProjectById();

    void updateProjectByIndex();

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
