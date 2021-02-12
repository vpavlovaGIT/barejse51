package ru.vpavlova.tm.command;

import ru.vpavlova.tm.model.Project;

public abstract class AbstractProjectCommand extends AbstractCommand {

    protected void showProject(final Project project) {
        if (project == null) throw new NullPointerException();
        System.out.println("ID: " + project.getId());
        System.out.println("NAME: " + project.getName());
        System.out.println("DESCRIPTION: " + project.getDescription());
        System.out.println("STATUS: " + project.getStatus().getDisplayName());
    }

}
