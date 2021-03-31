package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Optional;

public class ProjectByIndexRemoveCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "project-remove-by-index";
    }

    @NotNull
    @Override
    public String description() {
        return "Remove project by index.";
    }

    @Override
    public void execute() {
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        System.out.println("[REMOVE PROJECT]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        @NotNull final Project project = serviceLocator.getProjectService().removeByIndex(userId, index);
        Optional.ofNullable(project).orElseThrow(ProjectNotFoundException::new);
    }

}
