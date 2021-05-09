package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

public class ProjectClearCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "project-clear";
    }

    @NotNull
    @Override
    public String description() {
        return "Clear all projects.";
    }

    @Override
    public void execute() {
        System.out.println("[PROJECT CLEAR]");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        endpointLocator.getProjectEndpoint().clear(session);
        System.out.println("[OK]");
    }

}
