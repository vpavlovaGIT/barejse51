package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.IProjectRepository;
import ru.vpavlova.tm.api.IProjectService;
import ru.vpavlova.tm.model.Project;
import java.util.List;

public class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository;

    public ProjectService(final IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project add(final String name, final String description) {
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        projectRepository.add(project);
        return project;
    }

    @Override
    public void add(final Project project) {
        if (project == null) return;
        projectRepository.add(project);
    }

    @Override
    public void remove(final Project project) {
        if (project == null) return;
        projectRepository.remove(project);
    }

    @Override
    public void clear() {
        projectRepository.clear();
    }

    @Override
    public Project findOneById(final String id) {
        if (id == null || id.isEmpty()) return null;
        return projectRepository.findOneById(id);
    }

    @Override
    public Project findOneByIndex(final Integer index) {
        if (index == null || index < 0) return null;
        return projectRepository.findOneByIndex(index);
    }

    @Override
    public Project findOneByName(final String name) {
        if (name == null || name.isEmpty()) return null;
        return projectRepository.findOneByName(name);
    }

    @Override
    public Project removeOneById(final String id) {
        if (id == null || id.isEmpty()) return null;
        return projectRepository.removeOneById(id);
    }

    @Override
    public Project removeOneByIndex(final Integer index) {
        if (index == null || index < 0) return null;
        return projectRepository.removeOneByIndex(index);
    }

    @Override
    public Project removeOneByName(final String name) {
        if (name == null || name.isEmpty()) return null;
        return projectRepository.removeOneByName(name);
    }

    @Override
    public Project updateTaskById(final String id, final String name, final String description) {
        if (id == null || id.isEmpty()) return null;
        if (name == null || name.isEmpty()) return null;
        final Project project = findOneById(id);
        if (project == null) return null;
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    @Override
    public Project updateTaskByIndex(final Integer index, final String name, final String description) {
        if (index == null || index < 0) return null;
        if (name == null || name.isEmpty()) return null;
        final Project project = findOneByIndex(index);
        if (project == null) return null;
        project.setName(name);
        project.setDescription(description);
        return project;
    }

}
