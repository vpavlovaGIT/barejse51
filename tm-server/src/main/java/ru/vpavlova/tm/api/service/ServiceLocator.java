package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.dto.*;
import ru.vpavlova.tm.api.service.model.*;

public interface ServiceLocator {

    @NotNull
    ITaskGraphService getTaskService();

    @NotNull
    IProjectGraphService getProjectService();

    @NotNull
    IProjectTaskGraphService getProjectTaskService();

    @NotNull
    IUserGraphService getUserService();

    @NotNull
    IPropertyService getPropertyService();

    @NotNull
    ISessionService getSessionService();

    @NotNull
    IBackupService getDataService();

    @NotNull
    IConnectionService getConnectionService();

    @NotNull
    IProjectService getProjectDTOService();

    @NotNull
    IProjectTaskService getProjectTaskDTOService();

    @NotNull
    ISessionService getSessionDTOService();

    @NotNull
    ITaskService getTaskDTOService();

    @NotNull
    IUserService getUserDTOService();

}
