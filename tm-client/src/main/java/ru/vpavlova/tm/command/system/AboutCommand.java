package ru.vpavlova.tm.command.system;

import com.jcabi.manifests.Manifests;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.command.AbstractCommand;

public class AboutCommand extends AbstractCommand {

    @NotNull
    @Override
    public String arg() {
        return "-a";
    }

    @NotNull
    @Override
    public String name() {
        return "about";
    }

    @NotNull
    @Override
    public String description() {
        return "Show developer info.";
    }

    @Override
    public void execute() {
        System.out.println("[ABOUT]");
        System.out.println(Manifests.read("developer"));
        System.out.println(Manifests.read("email"));
    }

}
