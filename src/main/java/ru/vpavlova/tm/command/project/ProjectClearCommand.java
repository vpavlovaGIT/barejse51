package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;

public class ProjectClearCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "project-clear";
    }

    @NotNull
    @Override
    public String description() {
        return "Clear all projects.";
    }

    @Override
    public void execute() {
        System.out.println("[PROJECT CLEAR]");
        serviceLocator.getProjectService().clear();
        System.out.println("[OK]");
    }

}
