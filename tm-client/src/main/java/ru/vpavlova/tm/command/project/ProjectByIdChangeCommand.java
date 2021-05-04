package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.Optional;

public class ProjectByIdChangeCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "change-project-status-by-id";
    }

    @NotNull
    @Override
    public String description() {
        return "Change project status by id.";
    }

    @Override
    public void execute() {
        System.out.println("[CHANGE PROJECT]");
        System.out.println("ENTER ID:");
        @NotNull final String id = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        @NotNull final String statusId = TerminalUtil.nextLine();
        @NotNull final Status status = Status.valueOf(statusId);
        @NotNull final Optional<Project> project = serviceLocator.getProjectService().changeStatusById(userId, id, status);
        if (!project.isPresent()) throw new ObjectNotFoundException();
    }

}