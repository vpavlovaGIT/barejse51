package ru.vpavlova.tm.api;

import ru.vpavlova.tm.model.Project;
import java.util.List;

public interface IProjectService {

    List<Project> findAll();

    Project add(String name, String description);

    void add(Project project);

    void remove(Project project);

    void clear();

    Project findOneById(String id);

    Project findOneByIndex(Integer index);

    Project findOneByName(String name);

    Project removeOneById(String id);

    Project removeOneByIndex(Integer index);

    Project removeOneByName(String name);

    Project updateTaskById(String id, String name, String description);

    Project updateTaskByIndex(Integer index, String name, String description);

}
