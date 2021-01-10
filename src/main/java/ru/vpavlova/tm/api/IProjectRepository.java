package ru.vpavlova.tm.api;

import ru.vpavlova.tm.model.Project;
import java.util.List;

public interface IProjectRepository {

    List<Project> findAll();

    void add(Project project);

    void remove(Project project);

    void clear();

}
