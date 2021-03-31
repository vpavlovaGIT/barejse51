package ru.vpavlova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

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
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        System.out.println("ENTER NEW PASSWORD:");
        @NotNull final String password = TerminalUtil.nextLine();
        @NotNull final Optional<User> user = serviceLocator.getUserService().setPassword(userId, password);
    }

}
