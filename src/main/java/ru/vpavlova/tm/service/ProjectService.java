package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.IProjectRepository;
import ru.vpavlova.tm.api.IProjectService;
import ru.vpavlova.tm.model.Project;
import ru.vpavlova.tm.model.Task;
import ru.vpavlova.tm.repository.ProjectRepository;
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
}
