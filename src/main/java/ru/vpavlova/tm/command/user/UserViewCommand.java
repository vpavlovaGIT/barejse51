package ru.vpavlova.tm.command.user;

import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.entity.User;

public class UserViewCommand extends AbstractCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "user-view-profile";
    }

    @Override
    public String description() {
        return "View user profile.";
    }

    @Override
    public void execute() {
        final User user = serviceLocator.getAuthService().getUser();
        System.out.println("[VIEW PROFILE]");
        System.out.println("LOGIN: " + user.getLogin());
        System.out.println("EMAIL: " + user.getEmail());
        System.out.println("FIRST NAME: " + user.getFirstName());
        System.out.println("LAST NAME: " + user.getLastName());
        System.out.println("MIDDLE NAME: " + user.getMiddleName());
        System.out.println("USER ID: " + user.getId());
    }

}
