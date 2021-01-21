package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.repository.ICommandRepository;
import ru.vpavlova.tm.constant.ArgumentConst;
import ru.vpavlova.tm.constant.TerminalConst;
import ru.vpavlova.tm.model.Command;
import ru.vpavlova.tm.util.TerminalUtil;

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

    private static final Command TASK_START_STATUS_BY_ID = new Command(
            TerminalConst.TASK_START_STATUS_BY_ID, null, "Start project status by id."
    );

    private static final Command TASK_START_STATUS_BY_INDEX = new Command(
            TerminalConst.TASK_START_STATUS_BY_INDEX, null, "Start project status by index."
    );

    private static final Command TASK_START_STATUS_BY_NAME = new Command(
            TerminalConst.TASK_START_STATUS_BY_NAME, null, "Start task status by name."
    );

    private static final Command TASK_FINISH_STATUS_BY_ID = new Command(
            TerminalConst.TASK_FINISH_STATUS_BY_ID, null, "Finish task status by id."
    );

    private static final Command TASK_FINISH_STATUS_BY_INDEX = new Command(
            TerminalConst.TASK_FINISH_STATUS_BY_INDEX, null, "Finish task status by index."
    );

    private static final Command TASK_FINISH_STATUS_BY_NAME = new Command(
            TerminalConst.TASK_FINISH_STATUS_BY_NAME, null, "Finish task status by name."
    );

    private static final Command TASK_UPDATE_STATUS_BY_ID = new Command(
            TerminalConst.TASK_UPDATE_STATUS_BY_ID, null, "Update task status by id."
    );

    private static final Command TASK_UPDATE_STATUS_BY_INDEX = new Command(
            TerminalConst.TASK_UPDATE_STATUS_BY_INDEX, null, "Update task status by index."
    );

    private static final Command TASK_UPDATE_STATUS_BY_NAME = new Command(
            TerminalConst.TASK_UPDATE_STATUS_BY_NAME, null, "Update task status by name."
    );

    private static final Command TASK_FIND_ALL_BY_PROJECT = new Command(
            TerminalConst.TASK_FIND_ALL_BY_PROJECT, null, "task-find-all-by-project-id"
    );

    private static final Command TASK_BIND_BY_PROJECT = new Command(
           TerminalConst.TASK_BIND_BY_PROJECT, null, "task-bind-by-project-id"
    );

    private static final Command TASK_UNBIND_BY_PROJECT = new Command(
            TerminalConst.TASK_UNBIND_BY_PROJECT, null, "task-unbind-by-project-id"
    );

    private static final Command TASK_REMOVE_AND_PROJECT_BY_PROJECT = new Command(
            TerminalConst.TASK_REMOVE_AND_PROJECT_BY_PROJECT, null, "task-remove-and-project-by-project-id"
    );

    private static final Command PROJECT_CREATE = new Command(
            TerminalConst.PROJECT_CREATE, null, "Create new project."
    );

    private static final Command PROJECT_CLEAR = new Command(
            TerminalConst.PROJECT_CLEAR, null, "Clear all projects."
    );

    private static final Command PROJECT_LIST = new Command(
            TerminalConst.PROJECT_LIST, null, "Show project list."
    );

    private static final Command PROJECT_VIEW_BY_ID = new Command(
            TerminalConst.PROJECT_VIEW_BY_ID, null, "Show project by id."
    );

    private static final Command PROJECT_VIEW_BY_INDEX = new Command(
            TerminalConst.PROJECT_VIEW_BY_INDEX, null, "Show project by index."
    );

    private static final Command PROJECT_VIEW_BY_NAME = new Command(
            TerminalConst.PROJECT_VIEW_BY_NAME, null, "Show project by name."
    );

    private static final Command PROJECT_REMOVE_BY_ID = new Command(
            TerminalConst.PROJECT_REMOVE_BY_ID, null, "Remove project by id."
    );

    private static final Command PROJECT_REMOVE_BY_INDEX = new Command(
            TerminalConst.PROJECT_REMOVE_BY_INDEX, null, "Remove project by index."
    );

    private static final Command PROJECT_REMOVE_BY_NAME = new Command(
            TerminalConst.PROJECT_REMOVE_BY_NAME, null, "Remove project by name."
    );

    private static final Command PROJECT_UPDATE_BY_ID = new Command(
            TerminalConst.PROJECT_UPDATE_BY_ID, null, "Update project by id."
    );

    private static final Command PROJECT_UPDATE_BY_INDEX = new Command(
            TerminalConst.PROJECT_UPDATE_BY_INDEX, null, "Update project by index."
    );

    private static final Command PROJECT_START_STATUS_BY_ID = new Command(
            TerminalConst.PROJECT_START_STATUS_BY_ID, null, "Start project status by id."
    );

    private static final Command PROJECT_START_STATUS_BY_INDEX = new Command(
            TerminalConst.PROJECT_START_STATUS_BY_INDEX, null, "Start project status by index."
    );

    private static final Command PROJECT_START_STATUS_BY_NAME = new Command(
            TerminalConst.PROJECT_START_STATUS_BY_NAME, null, "Start project status by name."
    );

    private static final Command PROJECT_FINISH_STATUS_BY_ID = new Command(
            TerminalConst.PROJECT_FINISH_STATUS_BY_ID, null, "Finish project status by id."
    );

    private static final Command PROJECT_FINISH_STATUS_BY_INDEX = new Command(
            TerminalConst.PROJECT_FINISH_STATUS_BY_INDEX, null, "Finish project status by index."
    );

    private static final Command PROJECT_FINISH_STATUS_BY_NAME = new Command(
            TerminalConst.PROJECT_FINISH_STATUS_BY_NAME, null, "Finish project status by name."
    );

    private static final Command PROJECT_UPDATE_STATUS_BY_ID = new Command(
            TerminalConst.PROJECT_UPDATE_STATUS_BY_ID, null, "Update project status by id."
    );

    private static final Command PROJECT_UPDATE_STATUS_BY_INDEX = new Command(
            TerminalConst.PROJECT_UPDATE_STATUS_BY_INDEX, null, "Update project status by index."
    );

    private static final Command PROJECT_UPDATE_STATUS_BY_NAME = new Command(
            TerminalConst.PROJECT_UPDATE_STATUS_BY_NAME, null, "Update project status by name."
    );

    private static final Command[] TERMINAL_COMMANDS = new Command[]{
            ABOUT, HELP, VERSION, INFO, ARGUMENTS, COMMANDS,
            TASK_LIST, TASK_CREATE, TASK_CLEAR, TASK_VIEW_BY_ID, TASK_VIEW_BY_INDEX, TASK_VIEW_BY_NAME,
            TASK_REMOVE_BY_ID, TASK_REMOVE_BY_INDEX, TASK_REMOVE_BY_NAME, TASK_UPDATE_BY_ID, TASK_UPDATE_BY_INDEX,
            TASK_START_STATUS_BY_ID, TASK_START_STATUS_BY_INDEX, TASK_START_STATUS_BY_NAME,
            TASK_FINISH_STATUS_BY_ID, TASK_FINISH_STATUS_BY_INDEX, TASK_FINISH_STATUS_BY_NAME,
            TASK_UPDATE_STATUS_BY_ID, TASK_UPDATE_STATUS_BY_INDEX, TASK_UPDATE_STATUS_BY_NAME,
            TASK_FIND_ALL_BY_PROJECT, TASK_BIND_BY_PROJECT, TASK_UNBIND_BY_PROJECT, TASK_REMOVE_AND_PROJECT_BY_PROJECT,
            PROJECT_LIST, PROJECT_CREATE, PROJECT_CLEAR, PROJECT_VIEW_BY_ID, PROJECT_VIEW_BY_INDEX, PROJECT_VIEW_BY_NAME,
            PROJECT_REMOVE_BY_ID, PROJECT_REMOVE_BY_INDEX, PROJECT_REMOVE_BY_NAME, PROJECT_UPDATE_BY_ID, PROJECT_UPDATE_BY_INDEX,
            PROJECT_START_STATUS_BY_ID, PROJECT_START_STATUS_BY_INDEX, PROJECT_START_STATUS_BY_NAME,
            PROJECT_FINISH_STATUS_BY_ID, PROJECT_FINISH_STATUS_BY_INDEX, PROJECT_FINISH_STATUS_BY_NAME,
            PROJECT_UPDATE_STATUS_BY_ID, PROJECT_UPDATE_STATUS_BY_INDEX, PROJECT_UPDATE_STATUS_BY_NAME,
            EXIT
    };

    public Command[] getTerminalCommands() {
        return TERMINAL_COMMANDS;
    }

}
