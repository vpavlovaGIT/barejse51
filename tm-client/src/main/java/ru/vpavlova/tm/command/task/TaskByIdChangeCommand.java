package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.endpoint.Status;
import ru.vpavlova.tm.endpoint.Task;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.Optional;

public class TaskByIdChangeCommand extends AbstractTaskCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "change-task-status-by-id";
    }

    @NotNull
    @Override
    public String description() {
        return "Change task status by id.";
    }

    @Override
    public void execute() {
        System.out.println("[CHANGE PROJECT]");
        System.out.println("ENTER ID:");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final String id = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        @NotNull final String statusId = TerminalUtil.nextLine();
        @NotNull final Status status = Status.valueOf(statusId);
        @NotNull final Task task = endpointLocator.getTaskEndpoint().changeTaskStatusById(session, id, status);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
    }

}
