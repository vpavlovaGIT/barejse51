package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

public class TaskByIdFinishCommand extends AbstractTaskCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "task-finish-status-by-id";
    }

    @Override
    public String description() {
        return "Finish task status by id.";
    }

    @Override
    public void execute() {
        System.out.println("[FINISH PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Task task = serviceLocator.getTaskService().finishProjectById(id);
        if (task == null) throw new TaskNotFoundException();
    }

}
