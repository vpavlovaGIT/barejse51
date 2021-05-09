package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.endpoint.Task;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class TaskByNameViewCommand extends AbstractTaskCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "task-view-by-name";
    }

    @NotNull
    @Override
    public String description() {
        return "Show task by name.";
    }

    @Override
    public void execute() {
        System.out.println("[SHOW TASK]");
        System.out.println("ENTER NAME:");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final String name = TerminalUtil.nextLine();
        @NotNull final Task task = endpointLocator.getTaskEndpoint().findTaskOneByName(session, name);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
    }

}
