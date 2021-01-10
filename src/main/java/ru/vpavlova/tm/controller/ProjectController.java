package ru.vpavlova.tm.controller;

import ru.vpavlova.tm.api.IProjectController;
import ru.vpavlova.tm.api.IProjectService;
import ru.vpavlova.tm.model.Project;
import ru.vpavlova.tm.model.Task;
import ru.vpavlova.tm.service.ProjectService;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.List;

public class ProjectController implements IProjectController {

    private final IProjectService projectService;

    public ProjectController(final IProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void showProjectList() {
        System.out.println("[PROJECT LIST]");
        final List<Project> projects = projectService.findAll();
        int index = 1;
        for (final Project project: projects) {
            System.out.println(index + ". " + project);
            index++;
        }
        System.out.println("[OK]");
    }

    @Override
    public void createProject() {
        System.out.println("[PROJECT CREATE]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Project project = projectService.add(name, description);
        if (project == null) {
            System.out.println("[FAIL]");
            return;
        }
        System.out.println("[OK]");
    }

    @Override
    public void clearProject() {
        System.out.println("[PROJECT CLEAR]");
        projectService.clear();
        System.out.println("[OK]");
    }

}
