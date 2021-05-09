package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.endpoint.Project;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class ProjectByNameViewCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "project-view-by-name";
    }

    @NotNull
    @Override
    public String description() {
        return "Show project by name.";
    }

    @Override
    public void execute() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER NAME:");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final String name = TerminalUtil.nextLine();
        @NotNull final Project project = endpointLocator.getProjectEndpoint().findProjectOneByName(session, name);
        Optional.ofNullable(project).orElseThrow(ProjectNotFoundException::new);
        showProject(project);
    }

}
