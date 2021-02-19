package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.List;

public class TaskFindAllByProjectIdCommand extends AbstractTaskCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "find-all-by-project-id";
    }

    @Override
    public String description() {
        return "Find all lby project id";
    }

    @Override
    public void execute() {
        System.out.println("[TASKS BY PROJECT]");
        System.out.println("[ENTER ID:]");
        final String projectId = TerminalUtil.nextLine();
        final List<Task> tasks = serviceLocator.getProjectTaskService().findAllTaskByProjectId(projectId);
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + ". " + task);
            index++;
        }
    }

}
