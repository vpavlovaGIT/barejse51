package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.entity.Project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Override
    public List<Project> findAll(final Comparator<Project> comparator) {
        final List<Project> projects = new ArrayList<>(entities);
        projects.sort(comparator);
        return projects;
    }

    @Override
    public Project findOneByName(final String userId, final String name) {
        for (final Project project : entities) {
            if (name.equals(project.getName()) && project.getUserId().equals(userId)) return project;
        }
        return null;
    }

    @Override
    public Project removeOneByName(final String userId, final String name) {
        final Project project = findOneByName(userId, name);
        removeOneByName(userId, name);
        return project;
    }

}
