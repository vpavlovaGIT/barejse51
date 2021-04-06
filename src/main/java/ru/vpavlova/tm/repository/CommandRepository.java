package ru.vpavlova.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.ICommandRepository;
import ru.vpavlova.tm.command.AbstractCommand;

import java.util.*;

public class CommandRepository implements ICommandRepository {

    @NotNull
    private final Map<String, AbstractCommand> arguments = new LinkedHashMap<>();

    @NotNull
    private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();

    @NotNull
    @Override
    public Collection<AbstractCommand> getCommands() {
        return commands.values();
    }

    @NotNull
    @Override
    public Collection<AbstractCommand> getArguments() {
        return arguments.values();
    }

    @NotNull
    @Override
    public Collection<String> getCommandNames() {
        @NotNull final List<String> result = new ArrayList<>();
        for (@Nullable final AbstractCommand command : commands.values()) {
            @Nullable final String name = command.name();
            if (!Optional.ofNullable(name).isPresent() || name.isEmpty()) continue;
            result.add(name);
        }
        return result;
    }

    @NotNull
    @Override
    public Collection<String> getCommandArgs() {
        @NotNull final List<String> result = new ArrayList<>();
        for (@Nullable final AbstractCommand command : commands.values()) {
            @Nullable final String arg = command.arg();
            if (!Optional.ofNullable(arg).isPresent() || arg.isEmpty()) continue;
            result.add(arg);
        }
        return result;
    }

    @NotNull
    @Override
    public AbstractCommand getCommandByName(@Nullable final String name) {
        return commands.get(name);
    }

    @NotNull
    @Override
    public AbstractCommand getCommandByArg(@Nullable final String arg) {
        return arguments.get(arg);
    }

    @Override
    public void add(AbstractCommand command) {
        @Nullable final String arg = command.arg();
        @Nullable final String name = command.name();
        if (Optional.ofNullable(arg).isPresent()) arguments.put(arg, command);
        if (Optional.ofNullable(name).isPresent()) commands.put(name, command);
    }
}
