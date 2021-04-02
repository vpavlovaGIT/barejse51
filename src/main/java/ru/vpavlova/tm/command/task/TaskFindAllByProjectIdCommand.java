package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.List;

public class TaskFindAllByProjectIdCommand extends AbstractTaskCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "find-all-by-project-id";
    }

    @NotNull
    @Override
    public String description() {
        return "Find all lby project id";
    }

    @Override
    public void execute() {
        System.out.println("[TASKS BY PROJECT]");
        System.out.println("[ENTER ID:]");
        @NotNull final String projectId = TerminalUtil.nextLine();
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        @NotNull final List<Task> tasks = serviceLocator.getProjectTaskService().findAllTaskByProjectId(userId, projectId);
        int index = 1;
        for (@NotNull final Task task : tasks) {
            System.out.println(index + ". " + task);
            index++;
        }
    }

}
