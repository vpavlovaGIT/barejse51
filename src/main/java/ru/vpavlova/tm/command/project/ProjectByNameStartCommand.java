package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

public class ProjectByNameStartCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "project-start-status-by-name";
    }

    @Override
    public String description() {
        return "Start project status by name.";
    }

    @Override
    public void execute() {
        System.out.println("[START PROJECT]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        final Project project = serviceLocator.getProjectService().startProjectByName(name);
        if (project == null) throw new ProjectNotFoundException();
    }

}
