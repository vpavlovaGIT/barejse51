package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.endpoint.Project;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;

import java.util.List;

public class ProjectListCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "project-list";
    }

    @NotNull
    @Override
    public String description() {
        return "Show project list.";
    }

    @Override
    public void execute() {
        System.out.println("[PROJECT LIST]");
        System.out.println("ENTER SORT:");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @Nullable List<Project> list = endpointLocator.getProjectEndpoint().findAllProjects(session);
        int index = 1;
        for (@NotNull final Project project : list) {
            System.out.println(index + ". " + project);
            index++;
        }
    }

}
