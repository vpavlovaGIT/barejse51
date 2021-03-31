package ru.vpavlova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.entity.User;

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
        @NotNull final Optional<User> user = serviceLocator.getAuthService().getUser();
        System.out.println("[VIEW PROFILE]");
        System.out.println("LOGIN: " + user.get().getLogin());
        System.out.println("EMAIL: " + user.get().getEmail());
        System.out.println("FIRST NAME: " + user.get().getFirstName());
        System.out.println("LAST NAME: " + user.get().getLastName());
        System.out.println("MIDDLE NAME: " + user.get().getMiddleName());
        System.out.println("USER ID: " + user.get().getId());
    }

}
