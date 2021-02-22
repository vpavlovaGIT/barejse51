package ru.vpavlova.tm.command.user;

import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.util.TerminalUtil;

public class UserChangePasswordCommand extends AbstractCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "user-change-password";
    }

    @Override
    public String description() {
        return "Change password user profile.";
    }

    @Override
    public void execute() {
        System.out.println("[CHANGE PASSWORD:]");
        final String userId = serviceLocator.getAuthService().getUserId();
        System.out.println("ENTER NEW PASSWORD:");
        final String password = TerminalUtil.nextLine();
        serviceLocator.getUserService().setPassword(userId, password);
    }

}
