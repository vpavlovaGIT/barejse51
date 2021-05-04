package ru.vpavlova.tm.command.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;

import java.util.Collection;
import java.util.Optional;

public class ArgumentsListCommand extends AbstractCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "arguments";
    }

    @NotNull
    @Override
    public String description() {
        return "Show program arguments.";
    }

    @Override
    public void execute() {
        @NotNull final Collection<String> arguments = serviceLocator.getCommandService().getListArgumentName();
        for (@NotNull final String argument: arguments) {
            if (!Optional.ofNullable(argument).isPresent()) continue;
            System.out.println(argument);
        }
    }

}
