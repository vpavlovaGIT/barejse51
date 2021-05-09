package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.endpoint.Project;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

public class ProjectByIdStartCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "project-start-status-by-id";
    }

    @NotNull
    @Override
    public String description() {
        return "Start project status by id.";
    }

    @Override
    public void execute() {
        System.out.println("[START PROJECT]");
        System.out.println("ENTER ID:");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final String id = TerminalUtil.nextLine();
        @NotNull final Project project = endpointLocator.getProjectEndpoint().startProjectById(session, id);
        if (project == null) throw new ProjectNotFoundException();
    }

}
