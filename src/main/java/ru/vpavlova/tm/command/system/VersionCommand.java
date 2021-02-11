package ru.vpavlova.tm.command.system;

import ru.vpavlova.tm.command.AbstractCommand;

public class VersionCommand extends AbstractCommand {
    
    @Override
    public String arg() {
        return "-v";
    }

    @Override
    public String name() {
        return "version";
    }

    @Override
    public String description() {
        return "Show application version.";
    }

    @Override
    public void execute() {
        System.out.println("[VERSION]");
        System.out.println("1.0.0");
    }

}
