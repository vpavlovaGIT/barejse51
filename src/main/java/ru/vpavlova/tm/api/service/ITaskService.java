package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.model.Project;
import ru.vpavlova.tm.model.Task;
import java.util.List;

public interface ITaskService {

    List<Task> findAll();

    Task add(String name, String description);

    void add(Task task);

    void remove(Task task);

    void clear();

    Task findOneById(String id);

    Task findOneByIndex(Integer index);

    Task findOneByName(String name);

    Task removeOneById(String id);

    Task removeOneByIndex(Integer index);

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
