package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.endpoint.Project;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.endpoint.Status;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Arrays;

public class ProjectByIdChangeCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "change-project-status-by-id";
    }

    @NotNull
    @Override
    public String description() {
        return "Change project status by id.";
    }

    @Override
    public void execute() {
        System.out.println("[CHANGE PROJECT]");
        System.out.println("ENTER ID:");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable
        final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull
        final String id = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        @NotNull
        final String statusId = TerminalUtil.nextLine();
        @NotNull
        final Status status = Status.valueOf(statusId);
        @Nullable
        final Project project = endpointLocator.getProjectEndpoint().changeProjectStatusById(session, id, status);
        if (project == null) throw new ProjectNotFoundException();
    }

}
