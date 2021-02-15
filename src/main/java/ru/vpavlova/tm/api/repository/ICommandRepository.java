package ru.vpavlova.tm.api.repository;

import ru.vpavlova.tm.command.AbstractCommand;

import java.util.Collection;

public interface ICommandRepository {

    Collection<AbstractCommand> getCommands();

    Collection<AbstractCommand> getArguments();

    Collection<String> getCommandNames();

    Collection<String> getCommandArgs();

    AbstractCommand getCommandByName(String name);

    AbstractCommand getCommandByArg(String name);

    void add(AbstractCommand command);

}
