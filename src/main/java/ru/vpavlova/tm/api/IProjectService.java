package ru.vpavlova.tm.api;

import ru.vpavlova.tm.model.Project;
import java.util.List;

public interface IProjectService {

    List<Project> findAll();

    Project add(String name, String description);

    void add(Project project);

    void remove(Project project);

    void clear();

}
