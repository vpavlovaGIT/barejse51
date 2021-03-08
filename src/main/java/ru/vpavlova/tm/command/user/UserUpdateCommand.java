package ru.vpavlova.tm.command.user;

import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.util.TerminalUtil;

public class UserUpdateCommand extends AbstractCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "user-update-profile";
    }

    @Override
    public String description() {
        return "Update user profile.";
    }

    @Override
    public void execute() {
        System.out.println("[UPDATE PROFILE]");
        final String userId = serviceLocator.getAuthService().getUserId();
        System.out.println("ENTER FIRST NAME:");
        final String firstName = TerminalUtil.nextLine();
        System.out.println("ENTER LAST NAME:");
        final String lastName = TerminalUtil.nextLine();
        System.out.println("ENTER MIDDLE NAME:");
        final String middleName = TerminalUtil.nextLine();
        serviceLocator.getUserService().updateUser(userId, firstName, lastName, middleName);
    }

}