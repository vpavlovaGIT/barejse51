package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

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
        final Task task = serviceLocator.getTaskService().removeOneById(taskId);
        if (task == null) throw new TaskNotFoundException();
    }

}
