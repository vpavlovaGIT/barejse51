package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.model.Task;
import ru.vpavlova.tm.util.TerminalUtil;

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
        System.out.println("[UPDATE TASK]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Task task = serviceLocator.getTaskService().findOneByIndex(index);
        if (task == null) throw new TaskNotFoundException();
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Task taskUpdatedIndex = serviceLocator.getTaskService().updateTaskByIndex(index, name, description);
        if (taskUpdatedIndex == null) throw new TaskNotFoundException();
    }

}
