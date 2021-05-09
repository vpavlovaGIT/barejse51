package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

public final class TaskByIdRemoveCommand extends AbstractTaskCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "task-remove-by-id";
    }

    @NotNull
    @Override
    public String description() {
        return "Remove task by id.";
    }

    @Override
    public void execute() {
        System.out.println("[REMOVE TASK]");
        System.out.println("ENTER ID:");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final String taskId = TerminalUtil.nextLine();
        endpointLocator.getTaskEndpoint().removeTaskById(taskId, session);
    }

}
