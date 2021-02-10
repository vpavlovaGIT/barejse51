package ru.vpavlova.tm.api.service;

public interface ServiceLocator {

    ITaskService getTaskService();

    IProjectService getProjectService();

    ICommandService getCommandService();

    IProjectTaskService getProjectTaskService();

}
