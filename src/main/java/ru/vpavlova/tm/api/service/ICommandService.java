package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;

import java.util.Collection;

public interface ICommandService {

    @Nullable
    AbstractCommand getCommandByName(String name);

    @Nullable
    AbstractCommand getCommandByArg(String arg);

    @NotNull
    Collection<AbstractCommand> getCommands();

    @NotNull
    Collection<AbstractCommand> getArguments();

    @Nullable
    Collection<String> getListArgumentName();

    @Nullable
    Collection<String> getListCommandName();

    void add(@Nullable AbstractCommand command);

}
