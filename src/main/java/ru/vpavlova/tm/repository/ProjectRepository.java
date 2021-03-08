package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.entity.Project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProjectRepository extends AbstractBusinessRepository<Project> implements IProjectRepository {

    @Override
    public List<Project> findAll(final Comparator<Project> comparator) {
        final List<Project> projects = new ArrayList<>(entities);
        projects.sort(comparator);
        return projects;
    }

}
