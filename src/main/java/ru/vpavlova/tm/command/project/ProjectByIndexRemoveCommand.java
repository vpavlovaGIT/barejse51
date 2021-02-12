package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.model.Project;
import ru.vpavlova.tm.util.TerminalUtil;

public class ProjectByIndexRemoveCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "project-remove-by-index";
    }

    @Override
    public String description() {
        return "Remove project by index.";
    }

    @Override
    public void execute() {
        System.out.println("[REMOVE PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Project project = serviceLocator.getProjectService().removeOneByIndex(index);
        if (project == null) throw new ProjectNotFoundException();
        showProject(project);
    }

}
