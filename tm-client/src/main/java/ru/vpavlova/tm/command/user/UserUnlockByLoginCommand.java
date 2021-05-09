package ru.vpavlova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

public class UserUnlockByLoginCommand extends AbstractCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "user-unlock";
    }

    @NotNull
    @Override
    public String description() {
        return "Unlock user by login.";
    }

    @Override
    public void execute() {
        System.out.println("Unlock User:");
        System.out.println("Enter Login:");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final String login = TerminalUtil.nextLine();
        endpointLocator.getAdminEndpoint().unlockUserByLogin(login, session);
    }

}
