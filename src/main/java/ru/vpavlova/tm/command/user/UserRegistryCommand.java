package ru.vpavlova.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.util.TerminalUtil;

public class UserRegistryCommand extends AbstractCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "user-registry";
    }

    @NotNull
    @Override
    public String description() {
        return "Registry user to program.";
    }

    @Override
    public void execute() {
        System.out.println("[REGISTRY]");
        System.out.println("[ENTER LOGIN]");
        @NotNull final String login = TerminalUtil.nextLine();
        System.out.println("[ENTER E-MAIL]");
        @NotNull final String email = TerminalUtil.nextLine();
        System.out.println("[ENTER PASSWORD]");
        @NotNull final String password = TerminalUtil.nextLine();
        serviceLocator.getAuthService().registry(login, password, email);
    }

}
