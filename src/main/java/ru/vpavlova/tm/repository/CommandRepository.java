package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.repository.ICommandRepository;
import ru.vpavlova.tm.command.AbstractCommand;

import java.util.*;

public class CommandRepository implements ICommandRepository {

    private final Map<String, AbstractCommand> arguments = new LinkedHashMap<>();

    private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();

    @Override
    public Collection<AbstractCommand> getCommands() {
        return commands.values();
    }

    @Override
    public Collection<AbstractCommand> getArguments() {
        return arguments.values();
    }

    @Override
    public Collection<String> getCommandNames() {
        final List<String> result = new ArrayList<>();
        for (final AbstractCommand command : commands.values()) {
            final String name = command.name();
            if (!Optional.ofNullable(name).isPresent() || name.isEmpty()) continue;
            result.add(name);
        }
        return result;
    }

    @Override
    public Collection<String> getCommandArgs() {
        final List<String> result = new ArrayList<>();
        for (final AbstractCommand command : commands.values()) {
            final String arg = command.arg();
            if (arg == null || arg.isEmpty()) continue;
            result.add(arg);
        }
        return result;
    }

    @Override
    public AbstractCommand getCommandByName(String name) {
        return commands.get(name);
    }

    @Override
    public AbstractCommand getCommandByArg(String name) {
        return arguments.get(name);
    }

    @Override
    public void add(AbstractCommand command) {
        final String arg = command.arg();
        final String name = command.name();
        if (Optional.ofNullable(arg).isPresent()) arguments.put(arg, command);
        if (Optional.ofNullable(name).isPresent()) commands.put(name, command);
    }
}
