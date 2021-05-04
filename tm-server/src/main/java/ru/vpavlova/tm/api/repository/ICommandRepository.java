package ru.vpavlova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;

import java.util.Collection;

public interface ICommandRepository {

    @NotNull
    Collection<AbstractCommand> getCommands();

    @NotNull
    Collection<AbstractCommand> getArguments();

    @NotNull
    Collection<String> getCommandNames();

    @NotNull
    Collection<String> getCommandArgs();

    @NotNull
    Collection<AbstractCommand> getArgsCommands();

    @NotNull
    AbstractCommand getCommandByName(@Nullable String name);

    @NotNull
    AbstractCommand getCommandByArg(@Nullable String name);

    void add(@Nullable AbstractCommand command);

}
