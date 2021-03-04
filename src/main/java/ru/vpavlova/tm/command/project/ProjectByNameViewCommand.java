package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

public class ProjectByNameViewCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "project-view-by-name";
    }

    @Override
    public String description() {
        return "Show project by name.";
    }

    @Override
    public void execute() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        final String userId = serviceLocator.getAuthService().getUserId();
        final Project project = serviceLocator.getProjectService().findOneByName(userId, name);
        if (project == null) throw new ProjectNotFoundException();
        showProject(project);
    }

}
