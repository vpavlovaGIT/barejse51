package ru.vpavlova.tm.controller;

import ru.vpavlova.tm.api.controller.IProjectController;
import ru.vpavlova.tm.api.service.IProjectService;
import ru.vpavlova.tm.api.service.IProjectTaskService;
import ru.vpavlova.tm.enumerated.Sort;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.entity.ProjectNotFoundException;
import ru.vpavlova.tm.model.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.List;

public class ProjectController implements IProjectController {

    private final IProjectService projectService;

    private final IProjectTaskService projectTaskService;

    public ProjectController(final IProjectService projectService, IProjectTaskService projectTaskService) {
        this.projectService = projectService;
        this.projectTaskService = projectTaskService;
    }

    @Override
    public void showProjectList() {
        System.out.println("[PROJECT LIST]");
        System.out.println("ENTER SORT:");
        System.out.println(Arrays.toString(Sort.values()));
        final String sort = TerminalUtil.nextLine();
        List<Project> list;
        if (sort == null || sort.isEmpty()) list = projectService.findAll();
        else {
            final Sort sortType = Sort.valueOf(sort);
            System.out.println(sortType.getDisplayName());
            list = projectService.findAll(sortType.getComparator());
        }

        int index = 1;
        for (final Project project : list) {
            System.out.println(index + ". " + project);
            index++;
        }
    }

    @Override
    public void createProject() {
        System.out.println("[PROJECT CREATE]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Project project = projectService.add(name, description);
        if (project == null) throw new ProjectNotFoundException();
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
        System.out.println("STATUS: " + project.getStatus().getDisplayName());
    }

    @Override
    public void showProjectById() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = projectService.findOneById(id);
        if (project == null) throw new ProjectNotFoundException();
        showProject(project);
    }

    @Override
    public void showProjectByIndex() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Project project = projectService.findOneByIndex(index);
        if (project == null) throw new ProjectNotFoundException();
        showProject(project);
    }

    @Override
    public void showProjectByName() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        final Project project = projectService.findOneByName(name);
        if (project == null) throw new ProjectNotFoundException();
        showProject(project);
    }

    @Override
    public void removeProjectById() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = projectService.removeOneById(id);
        if (project == null) throw new ProjectNotFoundException();
        showProject(project);
    }

    @Override
    public void removeProjectByIndex() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Project project = projectService.removeOneByIndex(index);
        if (project == null) throw new ProjectNotFoundException();
        showProject(project);
    }

    @Override
    public void removeProjectByName() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        final Project project = projectService.removeOneByName(name);
        if (project == null) throw new ProjectNotFoundException();
        showProject(project);
    }

    @Override
    public void updateProjectById() {
        System.out.println("[UPDATE PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = projectService.findOneById(id);
        if (project == null) throw new ProjectNotFoundException();
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Project projectUpdatedId = projectService.updateTaskById(id, name, description);
        if (projectUpdatedId == null) throw new ProjectNotFoundException();
    }

    @Override
    public void updateProjectByIndex() {
        System.out.println("[UPDATE PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Project project = projectService.findOneByIndex(index);
        if (project == null) throw new ProjectNotFoundException();
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Project projectUpdatedIndex = projectService.updateTaskByIndex(index, name, description);
        if (projectUpdatedIndex == null) throw new ProjectNotFoundException();
    }

    @Override
    public void startProjectById() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = projectService.startProjectById(id);
        if (project == null) throw new ProjectNotFoundException();
    }

    @Override
    public void startProjectByIndex() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Project project = projectService.startProjectByIndex(index);
        if (project == null) throw new ProjectNotFoundException();
    }

    @Override
    public void startProjectByName() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        final Project project = projectService.startProjectByName(name);
        if (project == null) throw new ProjectNotFoundException();
    }

    @Override
    public void finishProjectById() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = projectService.finishProjectById(id);
        if (project == null) throw new ProjectNotFoundException();
    }

    @Override
    public void finishProjectByIndex() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Project project = projectService.finishProjectByIndex(index);
        if (project == null) throw new ProjectNotFoundException();
    }

    @Override
    public void finishProjectByName() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        final Project project = projectService.finishProjectByName(name);
        if (project == null) throw new ProjectNotFoundException();
    }

    @Override
    public void changeProjectStatusById() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusId = TerminalUtil.nextLine();
        final Status status = Status.valueOf(statusId);
        final Project project = projectService.changeProjectStatusById(id, status);
        if (project == null) throw new ProjectNotFoundException();
    }

    @Override
    public void changeProjectStatusByIndex() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusId = TerminalUtil.nextLine();
        final Status status = Status.valueOf(statusId);
        final Project project = projectService.changeProjectStatusByIndex(index, status);
        if (project == null) throw new ProjectNotFoundException();
    }

    @Override
    public void changeProjectStatusByName() {
        System.out.println("[SHOW PROJECT]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusId = TerminalUtil.nextLine();
        final Status status = Status.valueOf(statusId);
        final Project project = projectService.changeProjectStatusByName(name, status);
        if (project == null) throw new ProjectNotFoundException();
    }

    @Override
    public void removeProjectAndTaskById() {
        System.out.println("[REMOVE ALL TASKS FROM PROJECT]");
        System.out.println("[ENTER ID]");
        final String projectId = TerminalUtil.nextLine();
        final Project project = projectTaskService.removeProjectById(projectId);
        if (project == null) throw new ProjectNotFoundException();
    }

}
