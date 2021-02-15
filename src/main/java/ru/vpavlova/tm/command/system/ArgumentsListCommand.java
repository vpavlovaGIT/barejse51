package ru.vpavlova.tm.command.system;

import ru.vpavlova.tm.command.AbstractCommand;

import java.util.Collection;

public class ArgumentsListCommand extends AbstractCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "arguments";
    }

    @Override
    public String description() {
        return "Show program arguments.";
    }

    @Override
    public void execute() {
        final Collection<String> arguments = serviceLocator.getCommandService().getListArgumentName();
        for (final String argument: arguments) {
            if (argument != null) System.out.println(argument);
        }
    }

}
