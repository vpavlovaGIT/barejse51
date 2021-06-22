package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.dto.*;
import ru.vpavlova.tm.api.service.model.*;

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

    @NotNull
    IBackupService getDataService();

    @NotNull
    IConnectionService getConnectionService();

    @NotNull
    IProjectDTOService getProjectDTOService();

    @NotNull
    IProjectTaskDTOService getProjectTaskDTOService();

    @NotNull
    ISessionDTOService getSessionDTOService();

    @NotNull
    ITaskDTOService getTaskDTOService();

    @NotNull
    IUserDTOService getUserDTOService();

}
