package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

public class ProjectByNameRemoveCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "project-remove-by-name";
    }

    @NotNull
    @Override
    public String description() {
        return "Remove project by name.";
    }

    @Override
    public void execute() {
        System.out.println("[REMOVE PROJECT]");
        System.out.println("ENTER NAME:");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final String name = TerminalUtil.nextLine();
        endpointLocator.getProjectEndpoint().removeProjectOneByName(session, name);
    }

}
