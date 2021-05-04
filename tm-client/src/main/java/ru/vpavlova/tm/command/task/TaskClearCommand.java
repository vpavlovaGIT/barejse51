package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;

public class TaskClearCommand extends AbstractTaskCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "task-clear";
    }

    @NotNull
    @Override
    public String description() {
        return "Clear all tasks.";
    }

    @Override
    public void execute() {
        System.out.println("[TASK CLEAR]");
        serviceLocator.getTaskService().clear();
        System.out.println("[OK]");
    }

}
