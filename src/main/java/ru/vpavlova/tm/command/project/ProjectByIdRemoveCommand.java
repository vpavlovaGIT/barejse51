package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

public class ProjectByIdRemoveCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "project-remove-by-id";
    }

    @Override
    public String description() {
        return "Remove project by id.";
    }

    @Override
    public void execute() {
        System.out.println("[REMOVE PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = serviceLocator.getProjectService().removeOneById(id);
        if (project == null) throw new ProjectNotFoundException();
    }

}
