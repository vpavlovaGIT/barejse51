package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Arrays;

public class TaskByIdChangeCommand extends AbstractTaskCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "change-task-status-by-id";
    }

    @Override
    public String description() {
        return "Change task status by id.";
    }

    @Override
    public void execute() {
        System.out.println("[CHANGE PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusId = TerminalUtil.nextLine();
        final Status status = Status.valueOf(statusId);
        final Task task = serviceLocator.getTaskService().changeProjectStatusById(id, status);
        if (task == null) throw new TaskNotFoundException();
    }

}