package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.entity.Project;

import java.util.Comparator;
import java.util.List;

public interface IProjectService extends IService<Project> {

    List<Project> findAll(Comparator<Project> comparator);

    Project add(String userId, String name, String description);

    Project findOneByName(String userId, String name);

    Project removeOneByName(String userId, String name);

    Project updateTaskById(String userId, String id, String name, String description);

    Project updateTaskByIndex(String userId, Integer index, String name, String description);

    Project startProjectById(String userId, String id);

    Project startProjectByIndex(String userId, Integer index);

    Project startProjectByName(String userId, String name);

    Project finishProjectById(String userId, String id);

    Project finishProjectByIndex(String userId, Integer index);

    Project finishProjectByName(String userId, String name);

    Project changeProjectStatusById(String userId, String id, Status status);

    Project changeProjectStatusByIndex(String userId, Integer index, Status status);

    Project changeProjectStatusByName(String userId, String name, Status status);

}
