package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class TaskByIdViewCommand extends AbstractTaskCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "task-view-by-id";
    }

    @NotNull
    @Override
    public String description() {
        return "Show task by id.";
    }

    @Override
    public void execute() {
        System.out.println("[SHOW TASK]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.nextLine();
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        @NotNull final Optional<Task> task = serviceLocator.getTaskService().findById(userId, id);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
        showTask(task);
    }

}
