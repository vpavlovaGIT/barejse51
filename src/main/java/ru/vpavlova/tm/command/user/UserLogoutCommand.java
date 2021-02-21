package ru.vpavlova.tm.command.user;

import ru.vpavlova.tm.command.AbstractCommand;

public class UserLogoutCommand extends AbstractCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "user-logout";
    }

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
