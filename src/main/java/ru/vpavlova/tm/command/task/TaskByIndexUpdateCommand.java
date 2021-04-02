package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
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
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        System.out.println("[UPDATE TASK]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        @NotNull final Optional<Task> task = serviceLocator.getTaskService().findByIndex(userId, index);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
        System.out.println("ENTER NAME:");
        @NotNull final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        @NotNull final String description = TerminalUtil.nextLine();
        @NotNull final Optional<Task> taskUpdatedIndex = serviceLocator.getTaskService().updateByIndex(userId, index, name, description);
        Optional.ofNullable(taskUpdatedIndex).orElseThrow(TaskNotFoundException::new);
    }

}
