package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class ProjectByNameViewCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "project-view-by-name";
    }

    @NotNull
    @Override
    public String description() {
        return "Show project by name.";
    }

    @Override
    public void execute() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER NAME:");
        @NotNull final String name = TerminalUtil.nextLine();
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        @NotNull final Optional<Project> project = serviceLocator.getProjectService().findByName(userId, name);
        Optional.ofNullable(project).orElseThrow(ProjectNotFoundException::new);
        showProject(project);
    }

}
