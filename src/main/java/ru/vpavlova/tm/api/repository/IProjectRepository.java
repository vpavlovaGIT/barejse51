package ru.vpavlova.tm.api.repository;

import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.Project;

import java.util.Comparator;
import java.util.List;

public interface IProjectRepository extends IRepository<Project> {

    List<Project> findAll(Comparator<Project> comparator);

    void remove(Project project);

    Project findOneByName(String name);

    Project removeOneByName(String name);

}
