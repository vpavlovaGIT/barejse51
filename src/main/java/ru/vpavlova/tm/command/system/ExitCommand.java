package ru.vpavlova.tm.command.system;

import ru.vpavlova.tm.command.AbstractCommand;

public class ExitCommand extends AbstractCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public String description() {
        return "Close application.";
    }

    @Override
    public void execute() {
        System.exit(0);
    }

}
