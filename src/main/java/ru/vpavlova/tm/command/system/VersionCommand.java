package ru.vpavlova.tm.command.system;

import com.jcabi.manifests.Manifests;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.command.AbstractCommand;

public class VersionCommand extends AbstractCommand {

    @NotNull
    @Override
    public String arg() {
        return "-v";
    }

    @NotNull
    @Override
    public String name() {
        return "version";
    }

    @NotNull
    @Override
    public String description() {
        return "Show application version.";
    }

    @Override
    public void execute() {
        System.out.println("[VERSION]");
        System.out.println(Manifests.read("version"));
    }

}
