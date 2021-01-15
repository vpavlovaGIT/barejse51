package ru.vpavlova.tm.controller;

import ru.vpavlova.tm.api.IProjectController;
import ru.vpavlova.tm.api.IProjectService;
import ru.vpavlova.tm.model.Project;
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
        for (final Project project : projects) {
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

    private void showProject(final Project project) {
        if (project == null) return;
        System.out.println("ID: " + project.getId());
        System.out.println("NAME: " + project.getName());
        System.out.println("DESCRIPTION: " + project.getDescription());
    }

    @Override
    public void showProjectById() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = projectService.findOneById(id);
        if (project == null) {
            System.out.println("[FAILED]");
            return;
        }
        showProject(project);
        System.out.println("[OK]");
    }

    @Override
    public void showProjectByIndex() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Project project = projectService.findOneByIndex(index);
        if (project == null) {
            System.out.println("[FAIL]");
            return;
        }
        showProject(project);
        System.out.println("[OK]");

    }

    @Override
    public void showProjectByName() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        final Project project = projectService.findOneByName(name);
        if (project == null) {
            System.out.println("[FAIL]");
            return;
        }
        showProject(project);
        System.out.println("[OK]");

    }

    @Override
    public void removeProjectById() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = projectService.removeOneById(id);
        if (project == null) {
            System.out.println("[FAIL]");
            return;
        }
        showProject(project);
        System.out.println("[OK]");

    }

    @Override
    public void removeProjectByIndex() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Project project = projectService.removeOneByIndex(index);
        if (project == null) {
            System.out.println("[FAIL]");
            return;
        }
        showProject(project);
        System.out.println("[OK]");
    }

    @Override
    public void removeProjectByName() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        final Project project = projectService.removeOneByName(name);
        if (project == null) {
            System.out.println("[FAIL]");
            return;
        }
        showProject(project);
        System.out.println("[OK]");
    }

    @Override
    public void updateProjectById() {
        System.out.println("[UPDATE PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = projectService.findOneById(id);
        if (project == null) {
            System.out.println("[FAIL]");
            return;
        }
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Project projectUpdatedId = projectService.updateTaskById(id, name, description);
        if (projectUpdatedId == null) {
            System.out.println("[FAIL]");
            return;
        }
        System.out.println("[OK]");
    }

    @Override
    public void updateProjectByIndex() {
        System.out.println("[UPDATE PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Project project = projectService.findOneByIndex(index);
        if (project == null) {
            System.out.println("[FAIL]");
            return;
        }
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Project projectUpdatedIndex = projectService.updateTaskByIndex(index, name, description);
        if (projectUpdatedIndex == null) {
            System.out.println("[FAIL]");
            return;
        }
        System.out.println("[OK]");
    }

}
