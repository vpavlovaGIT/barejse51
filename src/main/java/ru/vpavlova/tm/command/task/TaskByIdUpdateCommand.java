package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

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
        System.out.println("[UPDATE TASK]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Task task = serviceLocator.getTaskService().findOneById(id);
        if (task == null) throw new TaskNotFoundException();
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Task taskUpdatedId = serviceLocator.getTaskService().updateTaskById(id, name, description);
        if (taskUpdatedId == null) throw new TaskNotFoundException();
    }

}
