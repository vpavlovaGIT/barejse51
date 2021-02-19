package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

public class ProjectByIndexStartCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "project-start-status-by-index";
    }

    @Override
    public String description() {
        return "Start project status by index.";
    }

    @Override
    public void execute() {
        System.out.println("[START PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Project project = serviceLocator.getProjectService().startProjectByIndex(index);
        if (project == null) throw new ProjectNotFoundException();
    }

}
