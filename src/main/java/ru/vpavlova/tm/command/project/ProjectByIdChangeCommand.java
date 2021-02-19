package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Arrays;

public class ProjectByIdChangeCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "change-project-status-by-id";
    }

    @Override
    public String description() {
        return "Change project status by id.";
    }

    @Override
    public void execute() {
        System.out.println("[CHANGE PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusId = TerminalUtil.nextLine();
        final Status status = Status.valueOf(statusId);
        final Project project = serviceLocator.getProjectService().changeProjectStatusById(id, status);
        if (project == null) throw new ProjectNotFoundException();
    }

}