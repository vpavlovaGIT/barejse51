package ru.vpavlova.tm.api.repository;

import ru.vpavlova.tm.model.Command;

public interface ICommandRepository {

    Command[] getTerminalCommands();

}
