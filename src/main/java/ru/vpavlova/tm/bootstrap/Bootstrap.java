package ru.vpavlova.tm.bootstrap;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.repository.ICommandRepository;
import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.repository.IUserRepository;
import ru.vpavlova.tm.api.service.*;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.component.Backup;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.repository.CommandRepository;
import ru.vpavlova.tm.repository.ProjectRepository;
import ru.vpavlova.tm.repository.TaskRepository;
import ru.vpavlova.tm.repository.UserRepository;
import ru.vpavlova.tm.service.*;
import ru.vpavlova.tm.util.SystemUtil;
import ru.vpavlova.tm.util.TerminalUtil;

import java.io.File;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;

public class Bootstrap implements ServiceLocator {

    @NotNull
    private final ICommandRepository commandRepository = new CommandRepository();

    @NotNull
    private final ICommandService commandService = new CommandService(commandRepository);

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

    @NotNull
    private final Backup backup = new Backup(this);

    public void parseArg(@Nullable final String arg) {
        if (arg == null || arg.isEmpty()) return;
        @Nullable final AbstractCommand command = commandService.getCommandByArg(arg);
        if (command == null) return;
        command.execute();
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

    @SneakyThrows
    private void initCommands() {
        @NotNull final Reflections reflections = new Reflections("ru.vpavlova.tm.command");
        @NotNull final Set<Class<? extends AbstractCommand>> classes =
                reflections.getSubTypesOf(ru.vpavlova.tm.command.AbstractCommand.class);
        for (@NotNull final Class<? extends AbstractCommand> clazz : classes) {
            final boolean isAbstract = Modifier.isAbstract(clazz.getModifiers());
            if (isAbstract) continue;
            registry(clazz.newInstance());
        }
    }

    @SneakyThrows
    private void initPID() {
        @NotNull final String fileName = "task-manager.pid";
        @NotNull final String pid = Long.toString(SystemUtil.getPID());
        Files.write(Paths.get(fileName), pid.getBytes());
        @NotNull final File file = new File(fileName);
        file.deleteOnExit();
    }

    public void run(@Nullable final String... args) {
        loggerService.debug("TEST!!");
        loggerService.info("*** WELCOME TO TASK MANAGER ***");
        if (parseArgs(args)) System.exit(0);
        initPID();
        initCommands();
        initData();
        backup.init();
        while (true) {
            try {
                @NotNull final String command = TerminalUtil.nextLine();
                loggerService.command(command);
                parseCommand(command);
                System.out.println("[OK]");
            } catch (@NotNull final Exception e) {
                loggerService.error(e);
                System.err.println("[FAIL]");
            }
        }
    }

    public void parseCommand(@Nullable final String cmd) {
        if (!Optional.ofNullable(cmd).isPresent()) return;
        @Nullable final AbstractCommand command = commandService.getCommandByName(cmd);
        if (!Optional.ofNullable(command).isPresent()) return;
        @Nullable final Role[] roles = command.roles();
        authService.checkRole(roles);
        command.execute();
    }

    public boolean parseArgs(@Nullable final String[] args) {
        if (!Optional.ofNullable(args).isPresent() || args.length == 0) return false;
        @Nullable final String arg = args[0];
        parseArg(arg);
        return true;
    }

    private void registry(final AbstractCommand command) {
        if (!Optional.ofNullable(command).isPresent()) return;
        command.setServiceLocator(this);
        commandService.add(command);
    }

    @Override
    public ITaskService getTaskService() {
        return taskService;
    }

    @Override
    public IProjectService getProjectService() {
        return projectService;
    }

    @Override
    public ICommandService getCommandService() {
        return commandService;
    }

    @Override
    public IProjectTaskService getProjectTaskService() {
        return projectTaskService;
    }

    @Override
    public IUserService getUserService() {
        return userService;
    }

    @Override
    public IAuthService getAuthService() {
        return authService;
    }

    @NotNull
    @Override
    public IPropertyService getPropertyService() {
        return propertyService;
    }

}
