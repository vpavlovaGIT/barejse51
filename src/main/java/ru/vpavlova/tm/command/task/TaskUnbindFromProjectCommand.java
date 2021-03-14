package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

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
        final String userId = serviceLocator.getAuthService().getUserId();
        final Optional<Task> task = serviceLocator.getProjectTaskService().unbindTaskFromProject(userId, taskId);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
        System.out.println("TASK REMOVE FROM PROJECT");
    }

}
