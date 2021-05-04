package ru.vpavlova.tm.command.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;

public class ExitCommand extends AbstractCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "exit";
    }

    @NotNull
    @Override
    public String description() {
        return "Close application.";
    }

    @Override
    public void execute() {
        System.exit(0);
    }

}
