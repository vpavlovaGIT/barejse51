package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

public class ProjectByIndexRemoveCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "project-remove-by-index";
    }

    @NotNull
    @Override
    public String description() {
        return "Remove project by index.";
    }

    @Override
    public void execute() {
        System.out.println("[REMOVE PROJECT]");
        System.out.println("ENTER INDEX:");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        endpointLocator.getProjectEndpoint().removeProjectOneByIndex(session, index);
    }

}
