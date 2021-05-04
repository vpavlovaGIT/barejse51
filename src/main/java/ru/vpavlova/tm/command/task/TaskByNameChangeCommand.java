package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.Optional;

public class TaskByNameChangeCommand extends AbstractTaskCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "change-task-status-by-name";
    }

    @NotNull
    @Override
    public String description() {
        return "Change task status by name.";
    }

    @Override
    public void execute() {
        System.out.println("[CHANGE PROJECT]");
        System.out.println("ENTER NAME:");
        @NotNull final String name = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        @NotNull final String statusId = TerminalUtil.nextLine();
        @NotNull final Status status = Status.valueOf(statusId);
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        @NotNull final Optional<Task> task = serviceLocator.getTaskService().changeStatusByName(userId, name, status);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
    }

}
