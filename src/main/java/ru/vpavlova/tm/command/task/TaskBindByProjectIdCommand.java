package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

public class TaskBindByProjectIdCommand extends AbstractTaskCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "bind-task-by-project";
    }

    @Override
    public String description() {
        return "Bind task by project.";
    }

    @Override
    public void execute() {
        System.out.println("[BIND TASK WITH PROJECT]");
        System.out.println("[ENTER PROJECT ID:]");
        final String projectId = TerminalUtil.nextLine();
        System.out.println("[ENTER TASK ID:]");
        final String taskId = TerminalUtil.nextLine();
        final Task task = serviceLocator.getProjectTaskService().bindTaskByProject(projectId, taskId);
        if (task == null) throw new TaskNotFoundException();
        System.out.println("TASK ADD TO PROJECT");
    }

}
