package ru.vpavlova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.util.TerminalUtil;

public class UserLoginCommand extends AbstractCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "user-login";
    }

    @NotNull
    @Override
    public String description() {
        return "Login to program.";
    }

    @Override
    public void execute() {
        System.out.println("[LOGIN]");
        System.out.println("ENTER LOGIN:");
        @NotNull final String login = TerminalUtil.nextLine();
        System.out.println("ENTER PASSWORD:");
        @NotNull final String password = TerminalUtil.nextLine();
        serviceLocator.getAuthService().login(login, password);
    }

}
