package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public final class TaskByIdRemoveCommand extends AbstractTaskCommand {
    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "task-remove-by-id";
    }

    @Override
    public String description() {
        return "Remove task by id.";
    }

    @Override
    public void execute() {
        System.out.println("[REMOVE TASK]");
        System.out.println("ENTER ID:");
        final String taskId = TerminalUtil.nextLine();
        final String userId = serviceLocator.getAuthService().getUserId();
        final Task task = serviceLocator.getTaskService().removeById(userId, taskId);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
    }

}
