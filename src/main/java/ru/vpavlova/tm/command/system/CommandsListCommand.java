package ru.vpavlova.tm.command.system;

import ru.vpavlova.tm.command.AbstractCommand;

import java.util.Collection;

public class CommandsListCommand extends AbstractCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "commands";
    }

    @Override
    public String description() {
        return "Show program commands.";
    }

    @Override
    public void execute() {
        final Collection<String> commands = serviceLocator.getCommandService().getListCommandName();
        for (final String command : commands) {
            if (command != null) System.out.println(command);
        }
    }

}
