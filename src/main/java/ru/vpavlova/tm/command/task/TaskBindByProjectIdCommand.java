package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

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
        final String userId = serviceLocator.getAuthService().getUserId();
        final Optional<Task> task = serviceLocator.getProjectTaskService().bindTaskByProject(userId, projectId, taskId);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
        System.out.println("TASK ADD TO PROJECT");
    }

}
