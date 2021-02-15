package ru.vpavlova.tm.command.system;

import ru.vpavlova.tm.command.AbstractCommand;

import java.util.Collection;

public class HelpCommand extends AbstractCommand {

    @Override
    public String arg() {
        return "-h";
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String description() {
        return "Show terminal commands.";
    }

    @Override
    public void execute() {
        System.out.println("[HELP]");
        final Collection<AbstractCommand> commands = serviceLocator.getCommandService().getCommands();
        for (final AbstractCommand command : commands)
            System.out.println(command.name() + ": " + command.description());
    }

}
