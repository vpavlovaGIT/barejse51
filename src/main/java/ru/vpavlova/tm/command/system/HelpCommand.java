package ru.vpavlova.tm.command.system;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.command.AbstractCommand;

import java.util.Collection;

public class HelpCommand extends AbstractCommand {

    @NotNull
    @Override
    public String arg() {
        return "-h";
    }

    @NotNull
    @Override
    public String name() {
        return "help";
    }

    @NotNull
    @Override
    public String description() {
        return "Show terminal commands.";
    }

    @Override
    public void execute() {
        System.out.println("[HELP]");
        @NotNull final Collection<AbstractCommand> commands = serviceLocator.getCommandService().getCommands();
        for (@NotNull final AbstractCommand command : commands)
            System.out.println(command.name() + ": " + command.description());
    }

}
