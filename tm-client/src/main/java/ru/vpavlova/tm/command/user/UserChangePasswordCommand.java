package ru.vpavlova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

public class UserChangePasswordCommand extends AbstractCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "user-change-password";
    }

    @NotNull
    @Override
    public String description() {
        return "Change password user profile.";
    }

    @Override
    public void execute() {
        System.out.println("[CHANGE PASSWORD:]");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        System.out.println("ENTER NEW PASSWORD:");
        @NotNull final String password = TerminalUtil.nextLine();
        endpointLocator.getAdminEndpoint().setUserPassword(session, session.getUserId(), password);
    }

}
