package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

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
        @NotNull final String taskId = TerminalUtil.nextLine();
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        @NotNull final Task task = serviceLocator.getTaskService().removeById(userId, taskId);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
    }

}
