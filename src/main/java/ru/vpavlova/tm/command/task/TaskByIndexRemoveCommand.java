package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class TaskByIndexRemoveCommand extends AbstractTaskCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "task-remove-by-index";
    }

    @Override
    public String description() {
        return "Remove task by index.";
    }

    @Override
    public void execute() {
        final String userId = serviceLocator.getAuthService().getUserId();
        System.out.println("[REMOVE TASK]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Task task = serviceLocator.getTaskService().removeByIndex(userId, index);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
    }

}
