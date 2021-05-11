package ru.vpavlova.tm.command.user;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

public class UserRemoveByLoginCommand extends AbstractCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "user-remove";
    }

    @NotNull
    @Override
    public String description() {
        return "Remove user by login.";
    }

    @Override
    @SneakyThrows
    public void execute() {
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        System.out.println("[REMOVE USER]");
        System.out.println("ENTER LOGIN:");
        @NotNull final String login = TerminalUtil.nextLine();
        endpointLocator.getAdminEndpoint().removeOneByLogin(session, login);
    }

}
