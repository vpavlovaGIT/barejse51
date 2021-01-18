package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.model.Command;

public interface ICommandService {

    Command[] getTerminalCommands();

}
