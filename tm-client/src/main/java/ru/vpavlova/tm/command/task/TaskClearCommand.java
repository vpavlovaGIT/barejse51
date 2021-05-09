package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

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
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        endpointLocator.getTaskEndpoint().clearTasks(session);
        System.out.println("[OK]");
    }

}
