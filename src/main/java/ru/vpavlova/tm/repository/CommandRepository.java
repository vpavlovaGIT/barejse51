package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.ICommandRepository;
import ru.vpavlova.tm.constant.ArgumentConst;
import ru.vpavlova.tm.constant.TerminalConst;
import ru.vpavlova.tm.model.Command;

public class CommandRepository implements ICommandRepository {

    private static final Command ABOUT = new Command(
            TerminalConst.CMD_ABOUT, ArgumentConst.ARG_ABOUT, "Show developer info."
    );

    private static final Command HELP = new Command(
            TerminalConst.CMD_HELP, ArgumentConst.ARG_HELP, "Show terminal commands."
    );

    private static final Command VERSION = new Command(
            TerminalConst.CMD_VERSION, ArgumentConst.ARG_VERSION, "Show application version."
    );

    private static final Command INFO = new Command(
            TerminalConst.CMD_INFO, ArgumentConst.ARG_INFO, "Show system info."
    );

    private static final Command EXIT = new Command(
            TerminalConst.CMD_EXIT, null, "Close application."
    );

    private static final Command ARGUMENTS = new Command(
            TerminalConst.CMD_ARGUMENTS, null, "Show program arguments."
    );

    private static final Command COMMANDS = new Command(
            TerminalConst.CMD_COMMANDS, null, "Show program commands."
    );

    private static final Command TASK_CREATE = new Command(
            TerminalConst.TASK_CREATE, null, "Create new task."
    );

    private static final Command TASK_CLEAR = new Command(
            TerminalConst.TASK_CLEAR, null, "Clear all tasks."
    );

    private static final Command TASK_LIST = new Command(
            TerminalConst.TASK_LIST, null, "Show task list."
    );

    private static final Command TASK_VIEW_BY_ID = new Command(
            TerminalConst.TASK_VIEW_BY_ID, null, "Show task by id."
    );

    private static final Command TASK_VIEW_BY_INDEX = new Command(
            TerminalConst.TASK_VIEW_BY_INDEX, null, "Show task by index."
    );

    private static final Command TASK_VIEW_BY_NAME = new Command(
            TerminalConst.TASK_VIEW_BY_NAME, null, "Show task by name."
    );

    private static final Command TASK_REMOVE_BY_ID = new Command(
            TerminalConst.TASK_REMOVE_BY_ID, null, "Remove task by id."
    );

    private static final Command TASK_REMOVE_BY_INDEX = new Command(
            TerminalConst.TASK_REMOVE_BY_INDEX, null, "Remove task by index."
    );

    private static final Command TASK_REMOVE_BY_NAME = new Command(
            TerminalConst.TASK_REMOVE_BY_NAME, null, "Remove task by name."
    );

    private static final Command TASK_UPDATE_BY_ID = new Command(
            TerminalConst.TASK_UPDATE_BY_ID, null, "Update task by id."
    );

    private static final Command TASK_UPDATE_BY_INDEX = new Command(
            TerminalConst.TASK_UPDATE_BY_INDEX, null, "Update task by index."
    );

    private static final Command PROJECT_CREATE = new Command(
            TerminalConst.CMD_PROJECT_CREATE, null, "Create new project."
    );

    private static final Command PROJECT_CLEAR = new Command(
            TerminalConst.CMD_PROJECT_CLEAR, null, "Clear all projects."
    );

    private static final Command PROJECT_LIST = new Command(
            TerminalConst.CMD_PROJECT_LIST, null, "Show project list."
    );

    private static final Command[] TERMINAL_COMMANDS = new Command[]{
            ABOUT, HELP, VERSION, INFO, ARGUMENTS, COMMANDS,
            TASK_LIST, TASK_CREATE, TASK_CLEAR, TASK_VIEW_BY_ID, TASK_VIEW_BY_INDEX, TASK_VIEW_BY_NAME,
            TASK_REMOVE_BY_ID, TASK_REMOVE_BY_INDEX, TASK_REMOVE_BY_NAME, TASK_UPDATE_BY_ID, TASK_UPDATE_BY_INDEX,
            PROJECT_LIST, PROJECT_CREATE, PROJECT_CLEAR,
            EXIT
    };

    public Command[] getTerminalCommands() {
        return TERMINAL_COMMANDS;
    }

}
