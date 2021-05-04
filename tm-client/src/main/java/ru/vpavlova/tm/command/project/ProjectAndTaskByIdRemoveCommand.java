package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class ProjectAndTaskByIdRemoveCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "remove-project-and-task-by-project-id";
    }

    @NotNull
    @Override
    public String description() {
        return "Remove project with tasks by id.";
    }

    @Override
    public void execute() {
        System.out.println("[REMOVE ALL TASKS FROM PROJECT]");
        System.out.println("[ENTER ID]");
        @NotNull final String projectId = TerminalUtil.nextLine();
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        @NotNull final Project project = serviceLocator.getProjectTaskService().removeProjectById(userId, projectId);
        Optional.ofNullable(project).orElseThrow(ProjectNotFoundException::new);
    }

}
