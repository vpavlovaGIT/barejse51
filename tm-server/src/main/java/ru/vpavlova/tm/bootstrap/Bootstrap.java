package ru.vpavlova.tm.bootstrap;

import com.jcraft.jsch.Session;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.endpoint.*;
import ru.vpavlova.tm.api.repository.*;
import ru.vpavlova.tm.api.service.*;
import ru.vpavlova.tm.endpoint.*;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.repository.*;
import ru.vpavlova.tm.service.*;
import ru.vpavlova.tm.util.SystemUtil;

import javax.xml.ws.Endpoint;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Getter
public class Bootstrap implements ServiceLocator {

    @NotNull
    public final ISessionRepository sessionRepository = new SessionRepository();

    @NotNull
    public final ISessionService sessionService = new SessionService(this, sessionRepository);

    @NotNull
    public final ISessionEndpoint sessionEndpoint = new SessionEndpoint(this);

    @NotNull
    public final ITaskEndpoint taskEndpoint = new TaskEndpoint(this);

    @NotNull
    public final IProjectEndpoint projectEndpoint = new ProjectEndpoint(this);

    @NotNull
    public final IUserEndpoint userEndpoint = new UserEndpoint(this);

    @NotNull
    public final IAdminEndpoint adminEndpoint = new AdminEndpoint(this);

    @NotNull
    private final ITaskRepository taskRepository = new TaskRepository();

    @NotNull
    private final ITaskService taskService = new TaskService(taskRepository);

    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepository();

    @NotNull
    private final IProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);

    @NotNull
    private final IProjectService projectService = new ProjectService(projectRepository);

    @NotNull
    private final ILoggerService loggerService = new LoggerService();

    @NotNull
    private final IUserRepository userRepository = new UserRepository();

    @NotNull
    public final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IUserService userService = new UserService(userRepository, propertyService);

    @NotNull
    private final IAuthService authService = new AuthService(userService, propertyService);

    @Nullable
    private Session session = null;

    public Bootstrap() {
    }

    public void initData() {
        @NotNull String userId = userService.create("test", "test", "test@test.ru").getId();
        projectService.add(userId, "DEMO 1", "description1").setStatus(Status.COMPLETE);
        projectService.add(userId, "BETA 2", "description2").setStatus(Status.IN_PROGRESS);
        projectService.add(userId, "LAMBDA 3", "description3").setStatus(Status.IN_PROGRESS);
        taskService.add(userId, "B_TASK 4", "bbb").setStatus(Status.NOT_STARTED);
        taskService.add(userId, "C_TASK 5", "ccc").setStatus(Status.COMPLETE);
        taskService.add(userId, "C_TASK 6", "ccc").setStatus(Status.COMPLETE);
        @NotNull String adminId = userService.create("admin", "admin", Role.ADMIN).getId();
        projectService.add(adminId, "OMEGA 4", "description4").setStatus(Status.NOT_STARTED);
        projectService.add(adminId, "GAMMA 5", "description5").setStatus(Status.COMPLETE);
        projectService.add(adminId, "GAMMA 6", "description6").setStatus(Status.IN_PROGRESS);
        taskService.add(adminId, "A_TASK 1", "aaa").setStatus(Status.COMPLETE);
        taskService.add(adminId, "D_TASK 2", "ddd").setStatus(Status.IN_PROGRESS);
        taskService.add(adminId, "E_TASK 3", "eee").setStatus(Status.IN_PROGRESS);
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

    public void init(@Nullable final String... args) {
        textWelcome();
        initPID();
        initData();
        initEndpoint();
    }

    public boolean parseArgs(@Nullable final String[] args) {
        if (!Optional.ofNullable(args).isPresent() || args.length == 0) return false;
        @Nullable final String arg = args[0];
        return true;
    }

}
