package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

public final class ProjectCreateCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "project-create";
    }

    @Override
    public String description() {
        return "Create new project.";
    }

    @Override
    public void execute() {
        System.out.println("[PROJECT CREATE]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Project project = serviceLocator.getProjectService().add(name, description);
        if (project == null) throw new ProjectNotFoundException();
    }

}
