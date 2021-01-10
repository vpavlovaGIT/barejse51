package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.IProjectRepository;
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

}
