package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.model.Project;
import ru.vpavlova.tm.util.TerminalUtil;

public class ProjectByIdViewCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "project-view-by-id";
    }

    @Override
    public String description() {
        return "Show project by id.";
    }

    @Override
    public void execute() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = serviceLocator.getProjectService().findOneById(id);
        if (project == null) throw new ProjectNotFoundException();
        showProject(project);
    }

}
