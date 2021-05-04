package ru.vpavlova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.enumerated.Role;
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
        @NotNull final String login = TerminalUtil.nextLine();
        serviceLocator.getUserService().unlockUserByLogin(login);
    }

    @NotNull
    @Override
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}
