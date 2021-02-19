package ru.vpavlova.tm.api.repository;

import ru.vpavlova.tm.entity.Project;

import java.util.Comparator;
import java.util.List;

public interface IProjectRepository {

    List<Project> findAll();

    List<Project> findAll(Comparator<Project> comparator);

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
