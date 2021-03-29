package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class ProjectAndTaskByIdRemoveCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "remove-project-and-task-by-project-id";
    }

    @Override
    public String description() {
        return "Remove project with tasks by id.";
    }

    @Override
    public void execute() {
        System.out.println("[REMOVE ALL TASKS FROM PROJECT]");
        System.out.println("[ENTER ID]");
        final String projectId = TerminalUtil.nextLine();
        final String userId = serviceLocator.getAuthService().getUserId();
        final Project project = serviceLocator.getProjectTaskService().removeProjectById(userId, projectId);
        Optional.ofNullable(project).orElseThrow(ProjectNotFoundException::new);
    }

}
