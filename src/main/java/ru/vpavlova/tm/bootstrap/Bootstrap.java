package ru.vpavlova.tm.bootstrap;

import ru.vpavlova.tm.api.repository.ICommandRepository;
import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.repository.IUserRepository;
import ru.vpavlova.tm.api.service.*;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.command.project.*;
import ru.vpavlova.tm.command.system.*;
import ru.vpavlova.tm.command.task.*;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.repository.CommandRepository;
import ru.vpavlova.tm.repository.ProjectRepository;
import ru.vpavlova.tm.repository.TaskRepository;
import ru.vpavlova.tm.repository.UserRepository;
import ru.vpavlova.tm.service.*;
import ru.vpavlova.tm.util.TerminalUtil;

public class Bootstrap implements ServiceLocator {

    private final ICommandRepository commandRepository = new CommandRepository();

    private final ICommandService commandService = new CommandService(commandRepository);

    private final ITaskRepository taskRepository = new TaskRepository();

    private final ITaskService taskService = new TaskService(taskRepository);

    private final IProjectRepository projectRepository = new ProjectRepository();

    private final IProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);

    private final IProjectService projectService = new ProjectService(projectRepository);

    private final ILoggerService loggerService = new LoggerService();

    private final IUserRepository userRepository = new UserRepository();

    private final IUserService userService = new UserService(userRepository);

    private final IAuthService authService = new AuthService(userService);

    private void initData() {
        projectService.add("DEMO 1", "description1").setStatus(Status.COMPLETE);
        projectService.add("BETA 2", "description2").setStatus(Status.IN_PROGRESS);
        projectService.add("LAMBDA 3", "description3").setStatus(Status.IN_PROGRESS);
        projectService.add("OMEGA 4", "description4").setStatus(Status.NOT_STARTED);
        projectService.add("GAMMA 5", "description5").setStatus(Status.COMPLETE);

        taskService.add("A_TASK 1", "aaa").setStatus(Status.COMPLETE);
        taskService.add("D_TASK 2", "ddd").setStatus(Status.IN_PROGRESS);
        taskService.add("E_TASK 3", "eee").setStatus(Status.IN_PROGRESS);
        taskService.add("B_TASK 4", "bbb").setStatus(Status.NOT_STARTED);
        taskService.add("C_TASK 5", "ccc").setStatus(Status.COMPLETE);
    }

    private void initUsers() {
        userService.create("test", "test", "user@mail.ru");
        userService.create("admin", "admin", Role.ADMIN);
    }

    public void run(final String... args) {
        loggerService.debug("TEST!!");
        loggerService.info("*** WELCOME TO TASK MANAGER ***");
        if (parseArgs(args)) System.exit(0);
        initData();
        initUsers();
        while (true) {
            try {
                final String command = TerminalUtil.nextLine();
                loggerService.command(command);
                parseCommand(command);
                System.out.println("[OK]");
            } catch (final Exception e) {
                loggerService.error(e);
                System.err.println("[FAIL]");
            }
        }
    }

    public void parseArg(final String arg) {
        if (arg == null || arg.isEmpty()) return;
        final AbstractCommand command = commandService.getCommandByArg(arg);
        if (command == null) return;
        command.execute();
    }

    {
        registry(new AboutCommand());
        registry(new ArgumentsListCommand());
        registry(new CommandsListCommand());
        registry(new ExitCommand());
        registry(new HelpCommand());
        registry(new SystemInfoCommand());
        registry(new VersionCommand());

        registry(new ProjectAndTaskByIdRemoveCommand());
        registry(new ProjectByIdChangeCommand());
        registry(new ProjectByIdFinishCommand());
        registry(new ProjectByIdRemoveCommand());
        registry(new ProjectByIdStartCommand());
        registry(new ProjectByIdUpdateCommand());
        registry(new ProjectByIdViewCommand());
        registry(new ProjectByIndexChangeCommand());
        registry(new ProjectByIndexFinishCommand());
        registry(new ProjectByIndexRemoveCommand());
        registry(new ProjectByIndexStartCommand());
        registry(new ProjectByIndexUpdateCommand());
        registry(new ProjectByIndexViewCommand());
        registry(new ProjectByNameChangeCommand());
        registry(new ProjectByNameFinishCommand());
        registry(new ProjectByNameRemoveCommand());
        registry(new ProjectByNameStartCommand());
        registry(new ProjectByNameViewCommand());
        registry(new ProjectClearCommand());
        registry(new ProjectCreateCommand());
        registry(new ProjectListCommand());

        registry(new TaskBindByProjectIdCommand());
        registry(new TaskByIdChangeCommand());
        registry(new TaskByIdFinishCommand());
        registry(new TaskByIdRemoveCommand());
        registry(new TaskByIdStartCommand());
        registry(new TaskByIdUpdateCommand());
        registry(new TaskByIdViewCommand());
        registry(new TaskByIndexChangeCommand());
        registry(new TaskByIndexFinishCommand());
        registry(new TaskByIndexRemoveCommand());
        registry(new TaskByIndexStartCommand());
        registry(new TaskByIndexUpdateCommand());
        registry(new TaskByIndexViewCommand());
        registry(new TaskByNameChangeCommand());
        registry(new TaskByNameFinishCommand());
        registry(new TaskByNameRemoveCommand());
        registry(new TaskByNameStartCommand());
        registry(new TaskByNameViewCommand());
        registry(new TaskClearCommand());
        registry(new TaskCreateCommand());
        registry(new TaskFindAllByProjectIdCommand());
        registry(new TaskListCommand());
        registry(new TaskUnbindFromProjectCommand());
    }

    public void parseCommand(final String cmd) {
        if (cmd == null || cmd.isEmpty()) return;
        final AbstractCommand command = commandService.getCommandByName(cmd);
        if (command == null) return;
        command.execute();
    }

    public boolean parseArgs(final String[] args) {
        if (args == null || args.length == 0) return false;
        final String arg = args[0];
        parseArg(arg);
        return true;
    }

    private void registry(final AbstractCommand command) {
        if (command == null) return;
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

}
