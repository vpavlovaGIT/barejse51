package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.IPropertyService;

public interface ServiceLocator {

    @NotNull
    ITaskService getTaskService();

    @NotNull
    IProjectService getProjectService();

    @NotNull
    IProjectTaskService getProjectTaskService();

    @NotNull
    IUserService getUserService();

    @NotNull
    IPropertyService getPropertyService();

    @NotNull
    ISessionService getSessionService();

}
