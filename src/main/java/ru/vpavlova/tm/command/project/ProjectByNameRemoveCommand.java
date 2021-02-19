package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

public class ProjectByNameRemoveCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "project-remove-by-name";
    }

    @Override
    public String description() {
        return "Remove project by name.";
    }

    @Override
    public void execute() {
        System.out.println("[REMOVE PROJECT]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        final Project project = serviceLocator.getProjectService().removeOneByName(name);
        if (project == null) throw new ProjectNotFoundException();
        showProject(project);
    }

}
