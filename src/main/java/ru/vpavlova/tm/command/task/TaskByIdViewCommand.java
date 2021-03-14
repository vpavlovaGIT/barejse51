package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class TaskByIdViewCommand extends AbstractTaskCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "task-view-by-id";
    }

    @Override
    public String description() {
        return "Show task by id.";
    }

    @Override
    public void execute() {
        System.out.println("[SHOW TASK]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final String userId = serviceLocator.getAuthService().getUserId();
        final Optional<Task> task = serviceLocator.getTaskService().findOneById(userId, id);
        if (!task.isPresent()) throw new TaskNotFoundException();
        showTask(task.get());
    }

}
