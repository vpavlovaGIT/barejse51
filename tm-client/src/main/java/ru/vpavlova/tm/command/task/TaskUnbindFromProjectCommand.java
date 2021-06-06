package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.endpoint.TaskEndpoint;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

public class TaskUnbindFromProjectCommand extends AbstractTaskCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "unbind-task-from-project";
    }

    @NotNull
    @Override
    public String description() {
        return "Unbind task from project.";
    }

    @Override
    public void execute() {
        System.out.println("[UNBIND TASK WITH PROJECT]");
        System.out.println("[ENTER PROJECT ID:]");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final String taskId = TerminalUtil.nextLine();
        @NotNull final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        taskEndpoint.unbindTaskFromProject(session, taskId);
        System.out.println("TASK REMOVE FROM PROJECT");
    }

}
