package ru.vpavlova.tm.bootstrap;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.endpoint.*;
import ru.vpavlova.tm.api.service.*;
import ru.vpavlova.tm.api.service.dto.*;
import ru.vpavlova.tm.api.service.model.*;
import ru.vpavlova.tm.dto.SessionDTO;
import ru.vpavlova.tm.endpoint.*;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.service.*;
import ru.vpavlova.tm.service.model.*;
import ru.vpavlova.tm.util.SystemUtil;

import javax.xml.ws.Endpoint;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Getter
public class Bootstrap implements ServiceLocator {

    @NotNull
    public final IPropertyService propertyService = new PropertyService();

    @NotNull
    public final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    public final ISessionService sessionService = new SessionService(connectionService, this);

    @NotNull
    public final ISessionEndpoint sessionEndpoint = new SessionEndpoint(this);

    @NotNull
    public final ITaskEndpoint taskEndpoint = new TaskEndpoint(this);

    @NotNull
    public final IProjectEndpoint projectEndpoint = new ProjectEndpoint(this);

    @NotNull
    public final IUserEndpoint userEndpoint = new UserEndpoint(this);

    @NotNull
    public final IAdminUserEndpoint adminEndpoint = new AdminUserEndpoint(this);

    @NotNull
    private final ITaskService taskService = new TaskService(connectionService);

    @NotNull
    private final IProjectTaskService projectTaskService = new ProjectTaskService(connectionService);

    @NotNull
    private final IProjectService projectService = new ProjectService(connectionService);

    @NotNull
    private final ILoggerService loggerService = new LoggerService();

    @NotNull
    private final IUserService userService = new UserService(propertyService, connectionService);

    @NotNull
    private final BackupService backupService = new BackupService(userService, taskService, projectService, sessionService);

    @NotNull
    private final IAdminDataEndpoint adminDataEndpoint = new AdminDataEndpoint(this, backupService);

    @NotNull
    public final IProjectDTOService projectDTOService = new ProjectDTOService(connectionService);

    @NotNull
    public final IProjectTaskDTOService projectTaskDTOService = new ProjectTaskDTOService(connectionService);

    @NotNull
    public final ISessionDTOService sessionDTOService = new SessionDTOService(connectionService, this);

    @NotNull
    public final ITaskDTOService taskDTOService = new TaskDTOService(connectionService);

    @NotNull
    public final IUserDTOService userDTOService = new UserDTOService(propertyService, connectionService);

    @Nullable
    private SessionDTO session = null;

    public Bootstrap() {
    }

    private void textWelcome() {
        loggerService.debug("TEST!!");
        loggerService.info("*** WELCOME TO TASK MANAGER ***");
    }

    private void initEndpoint() {
        registry(sessionEndpoint);
        registry(taskEndpoint);
        registry(projectEndpoint);
        registry(userEndpoint);
        registry(adminEndpoint);
        registry(adminDataEndpoint);
    }

    private void registry(@NotNull final Object endpoint) {
        @NotNull final String host = propertyService.getServerHost();
        @NotNull final String port = propertyService.getServerPort();
        @NotNull final String name = endpoint.getClass().getSimpleName();
        @NotNull final String wsdl = "http://" + host + ":" + port + "/" + name + "?wsdl";
        System.out.println(wsdl);
        Endpoint.publish(wsdl, endpoint);
    }

    @SneakyThrows
    private void initPID() {
        @NotNull final String fileName = "task-manager.pid";
        @NotNull final String pid = Long.toString(SystemUtil.getPID());
        Files.write(Paths.get(fileName), pid.getBytes());
        @NotNull final File file = new File(fileName);
        file.deleteOnExit();
    }

    public void initUser() {
        userService.create("test", "test");
        userService.create("admin", "admin", Role.ADMIN);
    }

    public void init(@Nullable final String... args) {
        textWelcome();
        initPID();
        initEndpoint();
        initUser();
    }

    @NotNull
    @Override
    public IBackupService getDataService() {
        return backupService;
    }

}
