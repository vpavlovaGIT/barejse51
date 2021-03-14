package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class TaskByIndexUpdateCommand extends AbstractTaskCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "task-update-status-by-index";
    }

    @Override
    public String description() {
        return "Update task status by index.";
    }

    @Override
    public void execute() {
        final String userId = serviceLocator.getAuthService().getUserId();
        System.out.println("[UPDATE TASK]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Optional<Task> task = serviceLocator.getTaskService().findOneByIndex(userId, index);
        if (!task.isPresent()) throw new TaskNotFoundException();
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Optional<Task> taskUpdatedIndex = serviceLocator.getTaskService().updateOneByIndex(userId, index, name, description);
        if (!taskUpdatedIndex.isPresent()) throw new TaskNotFoundException();
    }

}
