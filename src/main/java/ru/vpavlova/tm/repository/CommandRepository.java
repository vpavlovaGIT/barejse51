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

    private static final Command[] TERMINAL_COMMANDS = new Command[] {
            ABOUT, HELP, VERSION, INFO, EXIT, ARGUMENTS, COMMANDS
    };

    public Command[] getTerminalCommands() {
        return TERMINAL_COMMANDS;
    }

}
