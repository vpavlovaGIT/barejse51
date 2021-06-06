package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.endpoint.TaskEndpoint;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

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
        @NotNull final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        taskEndpoint.bindTaskByProject(session, projectId, taskId);
        System.out.println("TASK ADD TO PROJECT");
    }

}
