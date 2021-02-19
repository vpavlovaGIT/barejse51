package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

public class TaskByIndexStartCommand extends AbstractTaskCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "task-start-status-by-index";
    }

    @Override
    public String description() {
        return "Start task status by index.";
    }

    @Override
    public void execute() {
        System.out.println("[START PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Task task = serviceLocator.getTaskService().startProjectByIndex(index);
        if (task == null) throw new TaskNotFoundException();
    }

}
