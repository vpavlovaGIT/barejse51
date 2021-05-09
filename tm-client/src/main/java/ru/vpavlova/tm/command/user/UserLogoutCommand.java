package ru.vpavlova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

public class UserLogoutCommand extends AbstractCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "user-logout";
    }

    @NotNull
    @Override
    public String description() {
        return "Logout from program.";
    }

    @Override
    public void execute() {
        System.out.println("[LOGOUT]");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        endpointLocator.getSessionEndpoint().closeSession(session);
        bootstrap.setSession(null);
    }

}
