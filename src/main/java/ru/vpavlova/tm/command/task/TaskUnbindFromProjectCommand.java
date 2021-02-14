package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.model.Task;
import ru.vpavlova.tm.util.TerminalUtil;

public class TaskUnbindFromProjectCommand extends AbstractTaskCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "unbind-task-from-project";
    }

    @Override
    public String description() {
        return "Unbind task from project.";
    }

    @Override
    public void execute() {
        System.out.println("[UNBIND TASK WITH PROJECT]");
        System.out.println("[ENTER PROJECT ID:]");
        final String taskId = TerminalUtil.nextLine();
        final Task task = serviceLocator.getProjectTaskService().unbindTaskFromProject(taskId);
        if (task == null) throw new TaskNotFoundException();
        System.out.println("TASK REMOVE FROM PROJECT");
    }

}
