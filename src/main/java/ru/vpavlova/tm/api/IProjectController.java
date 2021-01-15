package ru.vpavlova.tm.api;

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

}
