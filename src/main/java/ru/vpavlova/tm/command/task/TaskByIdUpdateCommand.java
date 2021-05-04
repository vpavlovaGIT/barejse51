package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class TaskByIdUpdateCommand extends AbstractTaskCommand {

    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "task-update-status-by-id";
    }

    @NotNull
    @Override
    public String description() {
        return "Update task status by id.";
    }

    @Override
    public void execute() {
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        System.out.println("[UPDATE TASK]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.nextLine();
        @NotNull final Optional<Task> task = serviceLocator.getTaskService().findById(userId, id);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
        System.out.println("ENTER NAME:");
        @NotNull final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        @NotNull final String description = TerminalUtil.nextLine();
        @NotNull final Optional<Task> taskUpdatedId = serviceLocator.getTaskService().updateById(userId, id, name, description);
        Optional.ofNullable(taskUpdatedId).orElseThrow(TaskNotFoundException::new);
    }

}
