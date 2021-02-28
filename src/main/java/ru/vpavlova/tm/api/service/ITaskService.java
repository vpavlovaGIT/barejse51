package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.api.IService;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Task;

import java.util.Comparator;
import java.util.List;

public interface ITaskService extends IService<Task> {

    List<Task> findAll();

    List<Task> findAll(Comparator<Task> comparator);

    Task add(String name, String description);

    void remove(Task task);

    Task findOneByName(String name);

    Task removeOneByName(String name);

    Task updateTaskById(String id, String name, String description);

    Task updateTaskByIndex(Integer index, String name, String description);

    Task startProjectById(String id);

    Task startProjectByIndex(Integer index);

    Task startProjectByName(String name);

    Task finishProjectById(String id);

    Task finishProjectByIndex(Integer index);

    Task finishProjectByName(String name);

    Task changeProjectStatusById(String id, Status status);

    Task changeProjectStatusByIndex(Integer index, Status status);

    Task changeProjectStatusByName(String name, Status status);

}
