package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.ICommandRepository;
import ru.vpavlova.tm.api.service.ICommandService;
import ru.vpavlova.tm.command.AbstractCommand;

import java.util.Collection;

public class CommandService implements ICommandService {

    @NotNull
    private final ICommandRepository commandRepository;

    public CommandService(@NotNull ICommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Nullable
    @Override
    public AbstractCommand getCommandByName(@Nullable String name) {
        if (name == null || name.isEmpty()) return null;
        return commandRepository.getCommandByName(name);
    }

    @Nullable
    @Override
    public AbstractCommand getCommandByArg(@Nullable String arg) {
        if (arg == null || arg.isEmpty()) return null;
        return commandRepository.getCommandByArg(arg);
    }

    @NotNull
    @Override
    public Collection<AbstractCommand> getCommands() {
        return commandRepository.getCommands();
    }

    @NotNull
    @Override
    public Collection<AbstractCommand> getArguments() {
        return commandRepository.getArguments();
    }

    @NotNull
    @Override
    public Collection<String> getListArgumentName() {
        return commandRepository.getCommandArgs();
    }

    @NotNull
    @Override
    public Collection<String> getListCommandName() {
        return commandRepository.getCommandNames();
    }

    @Override
    public void add(@Nullable AbstractCommand command) {
        if (command == null) return;
        commandRepository.add(command);
    }

}
