package ru.vpavlova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;

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
        serviceLocator.getAuthService().logout();
    }

}
