package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.model.Project;
import ru.vpavlova.tm.util.TerminalUtil;

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
        final Project project = serviceLocator.getProjectService().findOneByIndex(index);
        if (project == null) throw new ProjectNotFoundException();
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Project projectUpdatedIndex = serviceLocator.getProjectService().updateTaskByIndex(index, name, description);
        if (projectUpdatedIndex == null) throw new ProjectNotFoundException();
    }

}
