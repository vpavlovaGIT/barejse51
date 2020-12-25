package ru.vpavlova.tm.api;

import ru.vpavlova.tm.model.Command;

public interface ICommandRepository {

    Command[] getTerminalCommands();

}
