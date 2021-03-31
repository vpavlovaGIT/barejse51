package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class ProjectByNameFinishCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "project-finish-status-by-name";
    }

    @NotNull
    @Override
    public String description() {
        return "Finish project status by name.";
    }

    @Override
    public void execute() {
        System.out.println("[FINISH PROJECT]");
        System.out.println("ENTER NAME:");
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        @NotNull final String name = TerminalUtil.nextLine();
        @NotNull final Optional<Project> project = serviceLocator.getProjectService().finishByName(userId, name);
        Optional.ofNullable(project).orElseThrow(ProjectNotFoundException::new);
    }

}
