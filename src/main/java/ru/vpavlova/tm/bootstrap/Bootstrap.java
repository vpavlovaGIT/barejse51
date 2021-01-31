package ru.vpavlova.tm.bootstrap;

import ru.vpavlova.tm.api.controller.ICommandController;
import ru.vpavlova.tm.api.controller.IProjectController;
import ru.vpavlova.tm.api.controller.ITaskController;
import ru.vpavlova.tm.api.repository.ICommandRepository;
import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.service.*;
import ru.vpavlova.tm.constant.ArgumentConst;
import ru.vpavlova.tm.constant.TerminalConst;
import ru.vpavlova.tm.controller.CommandController;
import ru.vpavlova.tm.controller.ProjectController;
import ru.vpavlova.tm.controller.TaskController;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.system.UnknownArgumentException;
import ru.vpavlova.tm.exception.system.UnknownCommandException;
import ru.vpavlova.tm.repository.CommandRepository;
import ru.vpavlova.tm.repository.ProjectRepository;
import ru.vpavlova.tm.repository.TaskRepository;
import ru.vpavlova.tm.service.*;
import ru.vpavlova.tm.util.TerminalUtil;

public class Bootstrap {

    private final ICommandRepository commandRepository = new CommandRepository();

    private final ICommandService commandService = new CommandService(commandRepository);

    private final ICommandController commandController = new CommandController(commandService);

    private final ITaskRepository taskRepository = new TaskRepository();

    private final ITaskService taskService = new TaskService(taskRepository);

    private final IProjectRepository projectRepository = new ProjectRepository();

    private final IProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);

    private final ITaskController taskController = new TaskController(taskService, projectTaskService);

    private final IProjectService projectService = new ProjectService(projectRepository);

    private final IProjectController projectController = new ProjectController(projectService, projectTaskService);

    private final ILoggerService loggerService = new LoggerService();

    private void initData() {
        projectService.add("DEMO 1", "description1").setStatus(Status.COMPLETE);
        projectService.add("BETA 2", "description2").setStatus(Status.IN_PROGRESS);
        projectService.add("LAMBDA 3", "description3").setStatus(Status.IN_PROGRESS);
        projectService.add("OMEGA 4", "description4").setStatus(Status.NOT_STARTED);
        projectService.add("GAMMA 5", "description5").setStatus(Status.COMPLETE);

        taskService.add("A_TASK 1","aaa").setStatus(Status.COMPLETE);
        taskService.add("D_TASK 2","ddd").setStatus(Status.IN_PROGRESS);
        taskService.add("E_TASK 3","eee").setStatus(Status.IN_PROGRESS);
        taskService.add("B_TASK 4", "bbb").setStatus(Status.NOT_STARTED);
        taskService.add("C_TASK 5", "ccc").setStatus(Status.COMPLETE);
    }

    public void run(final String... args) {
        loggerService.debug("TEST!!");
        loggerService.info("*** WELCOME TO TASK MANAGER ***");
        if (parseArgs(args)) System.exit(0);
        initData();
        while (true) {
            System.out.println("ENTER COMMAND: ");
            final String command = TerminalUtil.nextLine();
            loggerService.command(command);
            try {
                parseCommand(command);
                System.out.println("[OK]");
            } catch (final Exception e) {
                loggerService.error(e);
                System.err.println("[FAIL]");
            }
        }
    }

    public void parseArg(final String arg) {
        if (arg == null) return;
        switch (arg) {
            case ArgumentConst.ARG_ABOUT:
                commandController.showAbout();
                break;
            case ArgumentConst.ARG_HELP:
                commandController.showHelp();
                break;
            case ArgumentConst.ARG_VERSION:
                commandController.showVersion();
                break;
            case ArgumentConst.ARG_INFO:
                commandController.showSystemInfo();
                break;
            default: throw new UnknownArgumentException(arg);
        }
    }

    public void parseCommand(final String command) {
        if (command == null) return;
        switch (command) {
            case TerminalConst.CMD_ABOUT:
                commandController.showAbout();
                break;
            case TerminalConst.CMD_HELP:
                commandController.showHelp();
                break;
            case TerminalConst.CMD_VERSION:
                commandController.showVersion();
                break;
            case TerminalConst.CMD_INFO:
                commandController.showSystemInfo();
                break;
            case TerminalConst.CMD_COMMANDS:
                commandController.showCommands();
                break;
            case TerminalConst.CMD_ARGUMENTS:
                commandController.showArguments();
                break;
            case TerminalConst.CMD_EXIT:
                commandController.exit();
                break;
            case TerminalConst.TASK_LIST:
                taskController.showList();
                break;
            case TerminalConst.TASK_CREATE:
                taskController.create();
                break;
            case TerminalConst.TASK_CLEAR:
                taskController.clear();
                break;
            case TerminalConst.TASK_VIEW_BY_ID:
                taskController.showTaskById();
                break;
            case TerminalConst.TASK_VIEW_BY_INDEX:
                taskController.showTaskByIndex();
                break;
            case TerminalConst.TASK_VIEW_BY_NAME:
                taskController.showTaskByName();
                break;
            case TerminalConst.TASK_REMOVE_BY_ID:
                taskController.removeTaskById();
                break;
            case TerminalConst.TASK_REMOVE_BY_INDEX:
                taskController.removeTaskByIndex();
                break;
            case TerminalConst.TASK_REMOVE_BY_NAME:
                taskController.removeTaskByName();
                break;
            case TerminalConst.TASK_UPDATE_BY_ID:
                taskController.updateTaskById();
                break;
            case TerminalConst.TASK_UPDATE_BY_INDEX:
                taskController.updateTaskByIndex();
                break;
            case TerminalConst.TASK_START_STATUS_BY_ID:
                taskController.startProjectById();
                break;
            case TerminalConst.TASK_START_STATUS_BY_INDEX:
                taskController.startProjectByIndex();
                break;
            case TerminalConst.TASK_START_STATUS_BY_NAME:
                taskController.startProjectByName();
                break;
            case TerminalConst.TASK_FINISH_STATUS_BY_ID:
                taskController.finishProjectById();
                break;
            case TerminalConst.TASK_FINISH_STATUS_BY_INDEX:
                taskController.finishProjectByIndex();
                break;
            case TerminalConst.TASK_FINISH_STATUS_BY_NAME:
                taskController.finishProjectByName();
                break;
            case TerminalConst.TASK_UPDATE_STATUS_BY_ID:
                taskController.changeProjectStatusById();
                break;
            case TerminalConst.TASK_UPDATE_STATUS_BY_INDEX:
                taskController.changeProjectStatusByIndex();
                break;
            case TerminalConst.TASK_UPDATE_STATUS_BY_NAME:
                taskController.changeProjectStatusByName();
                break;
            case TerminalConst.TASK_FIND_ALL_BY_PROJECT:
                taskController.findAllTaskByProjectId();
                break;
            case TerminalConst.TASK_BIND_BY_PROJECT:
                taskController.bindTaskByProject();
                break;
            case TerminalConst.TASK_UNBIND_BY_PROJECT:
                taskController.unbindTaskFromProject();
                break;
            case TerminalConst.TASK_REMOVE_AND_PROJECT_BY_PROJECT:
                projectController.removeProjectAndTaskById();
                break;
            case TerminalConst.PROJECT_LIST:
                projectController.showProjectList();
                break;
            case TerminalConst.PROJECT_CREATE:
                projectController.createProject();
                break;
            case TerminalConst.PROJECT_CLEAR:
                projectController.clearProject();
                break;
            case TerminalConst.PROJECT_VIEW_BY_ID:
                projectController.showProjectById();
                break;
            case TerminalConst.PROJECT_VIEW_BY_INDEX:
                projectController.showProjectByIndex();
                break;
            case TerminalConst.PROJECT_VIEW_BY_NAME:
                projectController.showProjectByName();
                break;
            case TerminalConst.PROJECT_REMOVE_BY_ID:
                projectController.removeProjectById();
                break;
            case TerminalConst.PROJECT_REMOVE_BY_INDEX:
                projectController.removeProjectByIndex();
                break;
            case TerminalConst.PROJECT_REMOVE_BY_NAME:
                projectController.removeProjectByName();
                break;
            case TerminalConst.PROJECT_UPDATE_BY_ID:
                projectController.updateProjectById();
                break;
            case TerminalConst.PROJECT_UPDATE_BY_INDEX:
                projectController.updateProjectByIndex();
                break;
            case TerminalConst.PROJECT_START_STATUS_BY_ID:
                projectController.startProjectById();
                break;
            case TerminalConst.PROJECT_START_STATUS_BY_INDEX:
                projectController.startProjectByIndex();
                break;
            case TerminalConst.PROJECT_START_STATUS_BY_NAME:
                projectController.startProjectByName();
                break;
            case TerminalConst.PROJECT_FINISH_STATUS_BY_ID:
                projectController.finishProjectById();
                break;
            case TerminalConst.PROJECT_FINISH_STATUS_BY_INDEX:
                projectController.finishProjectByIndex();
                break;
            case TerminalConst.PROJECT_FINISH_STATUS_BY_NAME:
                projectController.finishProjectByName();
                break;
            case TerminalConst.PROJECT_UPDATE_STATUS_BY_ID:
                projectController.changeProjectStatusById();
                break;
            case TerminalConst.PROJECT_UPDATE_STATUS_BY_INDEX:
                projectController.changeProjectStatusByIndex();
                break;
            case TerminalConst.PROJECT_UPDATE_STATUS_BY_NAME:
                projectController.changeProjectStatusByName();
                break;
            default: throw new UnknownCommandException(command);
        }
    }

    public boolean parseArgs(final String[] args) {
        if (args == null || args.length == 0) return false;
        final String arg = args[0];
        parseArg(arg);
        return true;
    }

}
