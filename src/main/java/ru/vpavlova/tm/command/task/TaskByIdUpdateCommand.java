package ru.vpavlova.tm.command.task;

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

    @Override
    public String name() {
        return "task-update-status-by-id";
    }

    @Override
    public String description() {
        return "Update task status by id.";
    }

    @Override
    public void execute() {
        final String userId = serviceLocator.getAuthService().getUserId();
        System.out.println("[UPDATE TASK]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Optional<Task> task = serviceLocator.getTaskService().findOneById(userId, id);
        if (!task.isPresent()) throw new TaskNotFoundException();
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Optional<Task> taskUpdatedId = serviceLocator.getTaskService().updateOneById(userId, id, name, description);
        if (!taskUpdatedId.isPresent()) throw new TaskNotFoundException();
    }

}
