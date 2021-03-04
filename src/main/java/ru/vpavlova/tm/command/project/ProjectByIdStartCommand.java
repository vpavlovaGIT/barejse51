package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

public class ProjectByIdStartCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "project-start-status-by-id";
    }

    @Override
    public String description() {
        return "Start project status by id.";
    }

    @Override
    public void execute() {
        System.out.println("[START PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final String userId = serviceLocator.getAuthService().getUserId();
        final Project project = serviceLocator.getProjectService().startProjectById(userId, id);
        if (project == null) throw new ProjectNotFoundException();
    }

}
