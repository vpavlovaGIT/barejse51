package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.ICommandRepository;
import ru.vpavlova.tm.api.ICommandService;
import ru.vpavlova.tm.model.Command;

public class CommandService implements ICommandService {

    private final ICommandRepository commandRepository;

    public CommandService(ICommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public Command[] getTerminalCommands() {
        return commandRepository.getTerminalCommands();
    }

}
