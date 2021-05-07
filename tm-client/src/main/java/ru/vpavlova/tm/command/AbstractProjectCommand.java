package ru.vpavlova.tm.command;

import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.endpoint.Project;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;

import java.util.Optional;

public abstract class AbstractProjectCommand extends AbstractCommand {

    protected void showProject(@Nullable final Project project) {
        Optional.ofNullable(project).orElseThrow(ProjectNotFoundException::new);
        System.out.println("ID: " + project.getId());
        System.out.println("NAME: " + project.getName());
        System.out.println("DESCRIPTION: " + project.getDescription());
        System.out.println("STATUS: " + project.getStatus().value());
        System.out.println("Start Date: " + project.getDateStart());
        System.out.println("Finish Date: " + project.getDateFinish());
        System.out.println("Created: " + project.getCreated());
    }

}
