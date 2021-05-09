package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.endpoint.Task;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class TaskBindByProjectIdCommand extends AbstractTaskCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "bind-task-by-project";
    }

    @NotNull
    @Override
    public String description() {
        return "Bind task by project.";
    }

    @Override
    public void execute() {
        System.out.println("[BIND TASK WITH PROJECT]");
        System.out.println("[ENTER PROJECT ID:]");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final String projectId = TerminalUtil.nextLine();
        System.out.println("[ENTER TASK ID:]");
        @NotNull final String taskId = TerminalUtil.nextLine();
        @NotNull final Task task = endpointLocator.getTaskEndpoint().bindTaskByProject(session, projectId, taskId);
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
        System.out.println("TASK ADD TO PROJECT");
    }

}
