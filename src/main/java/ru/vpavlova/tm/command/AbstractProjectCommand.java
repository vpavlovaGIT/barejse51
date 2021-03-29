package ru.vpavlova.tm.command;

import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;

import java.util.Optional;

public abstract class AbstractProjectCommand extends AbstractCommand {

    protected void showProject(final Optional<Project> project) {
        Optional.ofNullable(project).orElseThrow(ProjectNotFoundException::new);
        System.out.println("ID: " + project.get().getId());
        System.out.println("NAME: " + project.get().getName());
        System.out.println("DESCRIPTION: " + project.get().getDescription());
        System.out.println("STATUS: " + project.get().getStatus().getDisplayName());
        System.out.println("Start Date: " + project.get().getDateStart());
        System.out.println("Finish Date: " + project.get().getDateFinish());
        System.out.println("Created: " + project.get().getCreated());
    }

}
