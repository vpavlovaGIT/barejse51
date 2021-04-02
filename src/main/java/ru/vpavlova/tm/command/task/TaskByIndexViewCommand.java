package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class TaskByIndexViewCommand extends AbstractTaskCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "task-view-by-index";
    }

    @NotNull
    @Override
    public String description() {
        return "Show task by index.";
    }

    @Override
    public void execute() {
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        System.out.println("[SHOW TASK]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        @NotNull final Optional<Task> task = serviceLocator.getTaskService().findByIndex(userId, index);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
    }

}
