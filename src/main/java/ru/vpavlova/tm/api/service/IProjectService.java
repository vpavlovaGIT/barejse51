package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.entity.Project;

import java.util.Comparator;
import java.util.List;

public interface IProjectService {

    List<Project> findAll();

    List<Project> findAll(Comparator<Project> comparator);

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

    Project startProjectById(String id);

    Project startProjectByIndex(Integer index);

    Project startProjectByName(String name);

    Project finishProjectById(String id);

    Project finishProjectByIndex(Integer index);

    Project finishProjectByName(String name);

    Project changeProjectStatusById(String id, Status status);

    Project changeProjectStatusByIndex(Integer index, Status status);

    Project changeProjectStatusByName(String name, Status status);

}
