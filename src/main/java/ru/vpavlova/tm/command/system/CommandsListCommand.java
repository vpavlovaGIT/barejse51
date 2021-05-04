package ru.vpavlova.tm.command.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;

import java.util.Collection;

public class CommandsListCommand extends AbstractCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "commands";
    }

    @NotNull
    @Override
    public String description() {
        return "Show program commands.";
    }

    @Override
    public void execute() {
        @NotNull final Collection<String> commands = serviceLocator.getCommandService().getListCommandName();
        for (@NotNull final String command : commands) {
            if (command != null) System.out.println(command);
        }
    }

}
