package ru.vpavlova.tm.command.user;

import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.util.TerminalUtil;

public class UserRemoveByLoginCommand extends AbstractCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "user-remove-by-login";
    }

    @Override
    public String description() {
        return "Remove by login user.";
    }

    @Override
    public void execute() {
        System.out.println("[REMOVE USER]");
        System.out.println("ENTER LOGIN:");
        final String login = TerminalUtil.nextLine();
        serviceLocator.getUserService().removeByLogin(login);
    }

}
