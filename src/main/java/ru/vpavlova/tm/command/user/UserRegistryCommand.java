package ru.vpavlova.tm.command.user;

import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.util.TerminalUtil;

public class UserRegistryCommand extends AbstractCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "user-registry";
    }

    @Override
    public String description() {
        return "Registry user to program.";
    }

    @Override
    public void execute() {
        System.out.println("[REGISTRY]");
        System.out.println("[ENTER LOGIN]");
        final String login = TerminalUtil.nextLine();
        System.out.println("[ENTER E-MAIL]");
        final String email = TerminalUtil.nextLine();
        System.out.println("[ENTER PASSWORD]");
        final String password = TerminalUtil.nextLine();
        serviceLocator.getAuthService().registry(login, password, email);
    }

}
