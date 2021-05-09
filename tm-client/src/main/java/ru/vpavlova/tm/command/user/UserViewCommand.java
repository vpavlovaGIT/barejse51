package ru.vpavlova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.endpoint.User;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

import java.util.Optional;

public class UserViewCommand extends AbstractCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "user-view-profile";
    }

    @NotNull
    @Override
    public String description() {
        return "View user profile.";
    }

    @Override
    public void execute() {
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final User user = endpointLocator.getUserEndpoint().findUserOneBySession(session);
        System.out.println("[VIEW PROFILE]");
        System.out.println("LOGIN: " + user.getLogin());
        System.out.println("EMAIL: " + user.getEmail());
        System.out.println("FIRST NAME: " + user.getFirstName());
        System.out.println("LAST NAME: " + user.getLastName());
        System.out.println("MIDDLE NAME: " + user.getMiddleName());
        System.out.println("USER ID: " + user.getId());
    }

}
