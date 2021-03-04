package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.service.IProjectService;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.exception.system.IndexIncorrectException;
import ru.vpavlova.tm.entity.Project;

import java.util.Comparator;
import java.util.List;

public class ProjectService extends AbstractService<Project> implements IProjectService {

    private final IProjectRepository projectRepository;

    public ProjectService(final IProjectRepository projectRepository) {
        super(projectRepository);
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll(Comparator<Project> comparator) {
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
        projectRepository.add(project);
        return project;
    }

    @Override
    public Project findOneByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        return projectRepository.findOneByName(userId, name);
    }

    @Override
    public Project removeOneByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        return projectRepository.removeOneByName(userId, name);
    }

    @Override
    public Project updateTaskById(final String userId, final String id, final String name, final String description) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Project project = findOneById(userId, id);
        if (project == null) return null;
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    @Override
    public Project updateTaskByIndex(final String userId, final Integer index, final String name, final String description) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Project project = findOneByIndex(index);
        if (project == null) return null;
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    @Override
    public Project startProjectById(final String userId, final String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final Project project = findOneById(userId, id);
        if (project == null) return null;
        project.setStatus(Status.IN_PROGRESS);
        return project;
    }

    @Override
    public Project startProjectByIndex(final String userId, final Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Project project = findOneByIndex(index);
        if (project == null) return null;
        project.setStatus(Status.IN_PROGRESS);
        return project;
    }

    @Override
    public Project startProjectByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Project project = findOneByName(userId, name);
        if (project == null) return null;
        project.setStatus(Status.IN_PROGRESS);
        return project;
    }

    @Override
    public Project finishProjectById(final String userId, final String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final Project project = findOneById(userId, id);
        if (project == null) return null;
        project.setStatus(Status.COMPLETE);
        return project;
    }

    @Override
    public Project finishProjectByIndex(final String userId, final Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Project project = findOneByIndex(index);
        if (project == null) return null;
        project.setStatus(Status.COMPLETE);
        return project;
    }

    @Override
    public Project finishProjectByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Project project = findOneByName(userId, name);
        if (project == null) return null;
        project.setStatus(Status.COMPLETE);
        return project;
    }

    @Override
    public Project changeProjectStatusById(final String userId, final String id, final Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final Project project = findOneById(userId, id);
        if (project == null) return null;
        project.setStatus(status);
        return project;
    }

    @Override
    public Project changeProjectStatusByIndex(final String userId, final Integer index, final Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (index == null) throw new IndexIncorrectException();
        final Project project = findOneByIndex(index);
        if (project == null) return null;
        project.setStatus(status);
        return project;
    }

    @Override
    public Project changeProjectStatusByName(final String userId, final String name, final Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Project project = findOneByName(userId, name);
        if (project == null) return null;
        project.setStatus(status);
        return project;
    }

}
