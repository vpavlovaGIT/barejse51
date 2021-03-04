package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

public class TaskCreateCommand extends AbstractTaskCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "task-create";
    }

    @Override
    public String description() {
        return "Create new task.";
    }

    @Override
    public void execute() {
        System.out.println("[TASK CREATE]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final String userId = serviceLocator.getAuthService().getUserId();
        final Task task = serviceLocator.getTaskService().add(userId, name, description);
        if (task == null) throw new TaskNotFoundException();
    }

}
