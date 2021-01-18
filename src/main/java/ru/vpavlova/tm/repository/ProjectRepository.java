package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.model.Project;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository implements IProjectRepository {

    private final List<Project> list = new ArrayList<>();

    @Override
    public List<Project> findAll() {
        return list;
    }

    @Override
    public void add(final Project project) {
        list.add(project);
    }

    @Override
    public void remove(final Project project) {
        list.remove(project);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Project findOneById(final String id) {
        for (final Project project : list) {
            if (id.equals(project.getId())) return project;
        }
        return null;
    }

    @Override
    public Project findOneByIndex(final Integer index) {
        return list.get(index);
    }

    @Override
    public Project findOneByName(final String name) {
        for (final Project project : list) {
            if (name.equals(project.getName())) return project;
        }
        return null;
    }

    @Override
    public Project removeOneById(final String id) {
        final Project project = findOneById(id);
        if (project == null) return null;
        list.remove(project);
        return project;
    }

    @Override
    public Project removeOneByIndex(final Integer index) {
        final Project project = findOneByIndex(index);
        if (project == null) return null;
        remove(project);
        return project;
    }

    @Override
    public Project removeOneByName(final String name) {
        final Project project = findOneByName(name);
        if (project == null) return null;
        remove(project);
        return project;
    }

}
