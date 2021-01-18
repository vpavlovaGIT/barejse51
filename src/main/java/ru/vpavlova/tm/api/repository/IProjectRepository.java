package ru.vpavlova.tm.api.repository;

import ru.vpavlova.tm.model.Project;
import java.util.List;

public interface IProjectRepository {

    List<Project> findAll();

    void add(Project project);

    void remove(Project project);

    void clear();

    Project findOneById(String id);

    Project findOneByIndex(Integer index);

    Project findOneByName(String name);

    Project removeOneById(String id);

    Project removeOneByIndex(Integer index);

    Project removeOneByName(String name);

}
