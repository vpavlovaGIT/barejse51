package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class ProjectByIndexUpdateCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "project-update-status-by-index";
    }

    @NotNull
    @Override
    public String description() {
        return "Update project status by index.";
    }

    @Override
    public void execute() {
        System.out.println("[UPDATE PROJECT]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        @NotNull final Optional<Project> project = serviceLocator.getProjectService().findByIndex(userId, index);
        Optional.ofNullable(project).orElseThrow(ProjectNotFoundException::new);
        System.out.println("ENTER NAME:");
        @NotNull final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        @NotNull final String description = TerminalUtil.nextLine();
        @NotNull final Optional<Project> projectUpdatedIndex = serviceLocator.getProjectService().updateByIndex(userId, index, name, description);
        Optional.ofNullable(projectUpdatedIndex).orElseThrow(ProjectNotFoundException::new);
    }

}
