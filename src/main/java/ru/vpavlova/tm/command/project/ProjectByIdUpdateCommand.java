package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class ProjectByIdUpdateCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "project-update-status-by-id";
    }

    @Override
    public String description() {
        return "Update project status by id.";
    }

    @Override
    public void execute() {
        System.out.println("[UPDATE PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final String userId = serviceLocator.getAuthService().getUserId();
        final Optional<Project> project = serviceLocator.getProjectService().findById(userId, id);
        if (!project.isPresent()) throw new ProjectNotFoundException();
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Optional<Project> projectUpdatedId = serviceLocator.getProjectService().updateById(userId, id, name, description);
        if (!projectUpdatedId.isPresent()) throw new ProjectNotFoundException();
    }

}
