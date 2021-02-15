package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.command.AbstractCommand;

import java.util.Collection;

public interface ICommandService {

    AbstractCommand getCommandByName(String name);

    AbstractCommand getCommandByArg(String arg);

    Collection<AbstractCommand> getCommands();

    Collection<AbstractCommand> getArguments();

    Collection<String> getListArgumentName();

    Collection<String> getListCommandName();

    void add(AbstractCommand command);

}
