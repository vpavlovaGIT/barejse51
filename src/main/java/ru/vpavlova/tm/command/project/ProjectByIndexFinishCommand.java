package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class ProjectByIndexFinishCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "project-finish-status-by-index";
    }

    @Override
    public String description() {
        return "Finish project status by index.";
    }

    @Override
    public void execute() {
        System.out.println("[FINISH PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final String userId = serviceLocator.getAuthService().getUserId();
        final Optional<Project> project = serviceLocator.getProjectService().finishOneByIndex(userId, index);
        if (!project.isPresent()) throw new ProjectNotFoundException();
    }

}
