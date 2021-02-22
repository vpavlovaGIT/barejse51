package ru.vpavlova.tm.command.user;

import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.util.TerminalUtil;

public class UserLoginCommand extends AbstractCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "user-login";
    }

    @Override
    public String description() {
        return "Login to program.";
    }

    @Override
    public void execute() {
        System.out.println("[LOGIN]");
        System.out.println("ENTER LOGIN:");
        final String login = TerminalUtil.nextLine();
        System.out.println("ENTER PASSWORD:");
        final String password = TerminalUtil.nextLine();
        serviceLocator.getAuthService().login(login, password);
    }

}
