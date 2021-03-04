package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Task;

import java.util.Comparator;
import java.util.List;

public interface ITaskService extends IService<Task> {

    List<Task> findAll(Comparator<Task> comparator);

    Task add(String userId, String name, String description);

    Task findOneByName(String userId, String name);

    Task removeOneByName(String userId, String name);

    Task updateTaskById(String userId, String id, String name, String description);

    Task updateTaskByIndex(String userId, Integer index, String name, String description);

    Task startProjectById(String userId, String id);

    Task startProjectByIndex(String userId, Integer index);

    Task startProjectByName(String userId, String name);

    Task finishProjectById(String userId, String id);

    Task finishProjectByIndex(String userId, Integer index);

    Task finishProjectByName(String userId, String name);

    Task changeProjectStatusById(String userId, String id, Status status);

    Task changeProjectStatusByIndex(String userId, Integer index, Status status);

    Task changeProjectStatusByName(String userId, String name, Status status);

}
