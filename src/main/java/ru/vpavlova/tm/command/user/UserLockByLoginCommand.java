package ru.vpavlova.tm.command.user;

import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.util.TerminalUtil;

public class UserLockByLoginCommand extends AbstractCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "user-lock";
    }

    @Override
    public String description() {
        return "Lock user by login.";
    }

    @Override
    public void execute() {
        System.out.println("Lock User:");
        System.out.println("Enter Login:");
        final String login = TerminalUtil.nextLine();
        serviceLocator.getUserService().lockUserByLogin(login);
    }

    @Override
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}
