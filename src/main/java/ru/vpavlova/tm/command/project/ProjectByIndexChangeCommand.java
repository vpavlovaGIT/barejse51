package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.Optional;

public class ProjectByIndexChangeCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "change-project-status-by-index";
    }

    @Override
    public String description() {
        return "Change project status by index.";
    }

    @Override
    public void execute() {
        System.out.println("[CHANGE PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String userId = serviceLocator.getAuthService().getUserId();
        final String statusId = TerminalUtil.nextLine();
        final Status status = Status.valueOf(statusId);
        final Optional<Project> project = serviceLocator.getProjectService().changeOneStatusByIndex(userId, index, status);
        if (!project.isPresent()) throw new ProjectNotFoundException();
    }

}
