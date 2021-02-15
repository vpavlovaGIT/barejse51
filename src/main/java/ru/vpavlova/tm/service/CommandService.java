package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.repository.ICommandRepository;
import ru.vpavlova.tm.api.service.ICommandService;
import ru.vpavlova.tm.command.AbstractCommand;

import java.util.Collection;

public class CommandService implements ICommandService {

    private final ICommandRepository commandRepository;

    public CommandService(ICommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public AbstractCommand getCommandByName(String name) {
        if (name == null || name.isEmpty()) return null;
        return commandRepository.getCommandByName(name);
    }

    @Override
    public AbstractCommand getCommandByArg(String arg) {
        if (arg == null || arg.isEmpty()) return null;
        return commandRepository.getCommandByArg(arg);
    }

    @Override
    public Collection<AbstractCommand> getCommands() {
        return commandRepository.getCommands();
    }

    @Override
    public Collection<AbstractCommand> getArguments() {
        return commandRepository.getArguments();
    }

    @Override
    public Collection<String> getListArgumentName() {
        return commandRepository.getCommandArgs();
    }

    @Override
    public Collection<String> getListCommandName() {
        return commandRepository.getCommandNames();
    }

    @Override
    public void add(AbstractCommand command) {
        if (command == null) return;
        commandRepository.add(command);
    }
    
}
