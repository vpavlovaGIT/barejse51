package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.service.IProjectService;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.entity.Project;

import java.util.Comparator;
import java.util.List;

public class ProjectService extends AbstractBusinessService<Project> implements IProjectService {

    private final IProjectRepository projectRepository;

    public ProjectService(final IProjectRepository projectRepository) {
        super(projectRepository);
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll(final String userId, Comparator<Project> comparator) {
        if (comparator == null) return null;
        return projectRepository.findAll(comparator);
    }

    @Override
    public Project add(final String userId, final String name, final String description) {
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        if (description == null || description.isEmpty()) return null;
        final Project project = new Project();
        project.setUserId(userId);
        project.setName(name);
        project.setDescription(description);
        projectRepository.add(userId, project);
        return project;
    }

}
