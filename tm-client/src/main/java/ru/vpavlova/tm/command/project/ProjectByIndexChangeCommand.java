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
import java.util.Optional;

public class ProjectByIndexChangeCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "change-project-status-by-index";
    }

    @NotNull
    @Override
    public String description() {
        return "Change project status by index.";
    }

    @Override
    public void execute() {
        System.out.println("[CHANGE PROJECT]");
        System.out.println("ENTER INDEX:");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        @NotNull
        final String statusId = TerminalUtil.nextLine();
        @NotNull
        final Status status = Status.valueOf(statusId);
        @NotNull
        final Project project = endpointLocator.getProjectEndpoint().findProjectByIndex(session, index);
        Optional.ofNullable(project).orElseThrow(ProjectNotFoundException::new);
        @NotNull
        final Project projectChange = endpointLocator.getProjectEndpoint().changeProjectStatusByIndex(session, index, status);
        if (projectChange == null) throw new ProjectNotFoundException();
    }

}
