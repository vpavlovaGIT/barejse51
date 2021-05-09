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

public class TaskByIndexUpdateCommand extends AbstractTaskCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "task-update-status-by-index";
    }

    @NotNull
    @Override
    public String description() {
        return "Update task status by index.";
    }

    @Override
    public void execute() {
        System.out.println("[UPDATE TASK]");
        System.out.println("ENTER INDEX:");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        @NotNull final Task task = endpointLocator.getTaskEndpoint().findTaskOneByIndex(session, index);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
        System.out.println("ENTER NAME:");
        @NotNull final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        @NotNull final String description = TerminalUtil.nextLine();
        @NotNull final Task taskUpdatedIndex = endpointLocator.getTaskEndpoint().updateTaskByIndex(session, index, name, description);
        Optional.ofNullable(taskUpdatedIndex).orElseThrow(TaskNotFoundException::new);
    }

}
