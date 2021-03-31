package ru.vpavlova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.enumerated.Role;
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
        @NotNull final String login = TerminalUtil.nextLine();
        serviceLocator.getUserService().lockUserByLogin(login);
    }

    @NotNull
    @Override
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}
