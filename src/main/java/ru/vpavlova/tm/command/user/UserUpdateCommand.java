package ru.vpavlova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class UserUpdateCommand extends AbstractCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "user-update-profile";
    }

    @NotNull
    @Override
    public String description() {
        return "Update user profile.";
    }

    @Override
    public void execute() {
        System.out.println("[UPDATE PROFILE]");
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        System.out.println("ENTER FIRST NAME:");
        @NotNull final String firstName = TerminalUtil.nextLine();
        System.out.println("ENTER LAST NAME:");
        @NotNull final String lastName = TerminalUtil.nextLine();
        System.out.println("ENTER MIDDLE NAME:");
        @NotNull final String middleName = TerminalUtil.nextLine();
        @NotNull final Optional<User> user = serviceLocator.getUserService().updateUser(userId, firstName, lastName, middleName);
        Optional.ofNullable(user).orElseThrow(ObjectNotFoundException::new);
    }

}
