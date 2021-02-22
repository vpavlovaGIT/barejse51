package ru.vpavlova.tm.command.user;

import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.util.TerminalUtil;

public class UserRemoveByIdCommand extends AbstractCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "user-remove-by-id";
    }

    @Override
    public String description() {
        return "Remove by id user.";
    }

    @Override
    public void execute() {
        System.out.println("[REMOVE USER]");
        System.out.println("ENTER ID:");
        final String userId = TerminalUtil.nextLine();
        serviceLocator.getUserService().removeById(userId);
    }

}
