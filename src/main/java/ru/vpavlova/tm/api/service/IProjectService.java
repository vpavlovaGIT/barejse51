package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.entity.Project;

import java.util.Comparator;
import java.util.List;

public interface IProjectService extends IService<Project> {

    List<Project> findAll(Comparator<Project> comparator);

    Project add(String name, String description);

    Project findOneByName(String name);

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
