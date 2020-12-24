package ru.vpavlova.tm.model;

import ru.vpavlova.tm.constant.ArgumentConst;
import ru.vpavlova.tm.constant.TerminalConst;

public class Command {

    public static final Command ABOUT = new Command(
            TerminalConst.CMD_ABOUT, ArgumentConst.ARG_ABOUT, "Show developer info."
    );

    public static final Command HELP = new Command(
            TerminalConst.CMD_HELP, ArgumentConst.ARG_HELP, "Show terminal commands."
    );

    public static final Command VERSION = new Command(
            TerminalConst.CMD_VERSION, ArgumentConst.ARG_VERSION, "Show application version."
    );

    public static final Command INFO = new Command(
            TerminalConst.CMD_INFO, ArgumentConst.ARG_INFO, "Show system info."
    );

    public static final Command EXIT = new Command(
            TerminalConst.CMD_EXIT, null, "Close application."
    );

    private String arg = "";

    private  String name = "";

    private String description = "";

    public Command() {
    }

    public Command(String name,String arg, String description) {
        this.name = name;
        this.arg = arg;
        this.description = description;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String result = "";
        if (name != null && !name.isEmpty()) result += name;
        if (arg != null && !arg.isEmpty()) result += " [" + arg + "] ";
        if (description != null && !description.isEmpty()) result += " - " + description;
        return result;
    }
}
