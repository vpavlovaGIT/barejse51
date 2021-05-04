package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class TaskByNameStartCommand extends AbstractTaskCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "task-start-status-by-name";
    }

    @NotNull
    @Override
    public String description() {
        return "Start task status by name.";
    }

    @Override
    public void execute() {
        System.out.println("[START PROJECT]");
        System.out.println("ENTER NAME:");
        @NotNull final String name = TerminalUtil.nextLine();
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        @NotNull final Optional<Task> task = serviceLocator.getTaskService().startByName(userId, name);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
    }

}