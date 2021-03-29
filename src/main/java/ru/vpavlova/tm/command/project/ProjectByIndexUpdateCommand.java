package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class ProjectByIndexUpdateCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "project-update-status-by-index";
    }

    @Override
    public String description() {
        return "Update project status by index.";
    }

    @Override
    public void execute() {
        System.out.println("[UPDATE PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final String userId = serviceLocator.getAuthService().getUserId();
        final Optional<Project> project = serviceLocator.getProjectService().findByIndex(userId, index);
        Optional.ofNullable(project).orElseThrow(ProjectNotFoundException::new);
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Optional<Project> projectUpdatedIndex = serviceLocator.getProjectService().updateByIndex(userId, index, name, description);
        Optional.ofNullable(projectUpdatedIndex).orElseThrow(ProjectNotFoundException::new);
    }

}
