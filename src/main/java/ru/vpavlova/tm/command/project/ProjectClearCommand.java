package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;

public class ProjectClearCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "project-clear";
    }

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
