package ru.vpavlova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

public class UserLockByLoginCommand extends AbstractCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "user-lock";
    }

    @NotNull
    @Override
    public String description() {
        return "Lock user by login.";
    }

    @Override
    public void execute() {
        System.out.println("Lock User:");
        System.out.println("Enter Login:");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final String login = TerminalUtil.nextLine();
        endpointLocator.getAdminEndpoint().lockUserByLogin(session, login);
    }

}
