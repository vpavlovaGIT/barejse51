package ru.vpavlova.tm.bootstrap;

import ru.vpavlova.tm.api.*;
import ru.vpavlova.tm.constant.ArgumentConst;
import ru.vpavlova.tm.constant.TerminalConst;
import ru.vpavlova.tm.controller.CommandController;
import ru.vpavlova.tm.controller.TaskController;
import ru.vpavlova.tm.repository.CommandRepository;
import ru.vpavlova.tm.repository.TaskRepository;
import ru.vpavlova.tm.service.CommandService;
import ru.vpavlova.tm.service.TaskService;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Scanner;

public class Bootstrap {

    private final ICommandRepository commandRepository = new CommandRepository();

    private final ICommandService commandService = new CommandService(commandRepository);

    private final ICommandController commandController = new CommandController(commandService);

    private final ITaskRepository taskRepository = new TaskRepository();

    private  final ITaskService taskService = new TaskService(taskRepository);

    private  final ITaskController taskController = new TaskController(taskService);

    public void run(final String... args) {
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        if (parseArgs(args)) System.exit(0);
        while (true) {
            System.out.println("ENTER COMMAND: ");
            final String command = TerminalUtil.nextLine();
            parseCommand(command);
        }
    }

    public void parseArg(final String arg) {
        if (arg == null) return;
        switch (arg) {
            case ArgumentConst.ARG_ABOUT: commandController.showAbout(); break;
            case ArgumentConst.ARG_HELP: commandController.showHelp(); break;
            case ArgumentConst.ARG_VERSION: commandController.showVersion(); break;
            case ArgumentConst.ARG_INFO: commandController.showSystemInfo(); break;
            default: showIncorrectCommand();
        }
    }

    public void parseCommand(final String command) {
        if (command == null) return;
        switch (command) {
            case TerminalConst.CMD_ABOUT: commandController.showAbout(); break;
            case TerminalConst.CMD_HELP: commandController.showHelp(); break;
            case TerminalConst.CMD_VERSION: commandController.showVersion(); break;
            case TerminalConst.CMD_INFO: commandController.showSystemInfo(); break;
            case TerminalConst.CMD_COMMANDS: commandController.showCommands(); break;
            case TerminalConst.CMD_ARGUMENTS: commandController.showArguments(); break;
            case TerminalConst.CMD_EXIT: commandController.exit(); break;
            case TerminalConst.CMD_TASK_LIST: taskController.showList(); break;
            case TerminalConst.CMD_TASK_CREATE: taskController.create(); break;
            case TerminalConst.CMD_TASK_CLEAR: taskController.clear(); break;
            default: showIncorrectCommand();
        }
    }

    public void showIncorrectArgument() {
        System.out.println("Error! Argument not found...");
    }

    public void showIncorrectCommand() {
        System.out.println("Error! Command not found...");
    }

    public boolean parseArgs(final String[] args) {
        if (args == null || args.length == 0) return false;
        final String arg = args[0];
        parseArg(arg);
        return true;
    }

}
