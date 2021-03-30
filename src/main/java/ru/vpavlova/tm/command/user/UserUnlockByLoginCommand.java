package ru.vpavlova.tm.command.user;

import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.util.TerminalUtil;

public class UserUnlockByLoginCommand extends AbstractCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "user-unlock";
    }

    @Override
    public String description() {
        return "Unlock user by login.";
    }

    @Override
    public void execute() {
        System.out.println("Unlock User:");
        System.out.println("Enter Login:");
        final String login = TerminalUtil.nextLine();
        serviceLocator.getUserService().unlockUserByLogin(login);
    }

    @Override
    public Role[] roles() {
        return new Role[]{Role.ADMIN};
    }

}
