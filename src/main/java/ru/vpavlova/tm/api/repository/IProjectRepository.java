package ru.vpavlova.tm.api.repository;

import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.Project;

import java.util.Comparator;
import java.util.List;

public interface IProjectRepository extends IRepository<Project> {

    List<Project> findAll(Comparator<Project> comparator);

    Project findOneByName(String userId, String name);

    Project removeOneByName(String userId, String name);

}
