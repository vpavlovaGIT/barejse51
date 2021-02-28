package ru.vpavlova.tm.api.repository;

import ru.vpavlova.tm.api.IRepository;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Task;

import java.util.Comparator;
import java.util.List;

public interface ITaskRepository extends IRepository<Task> {

    List<Task> findAll(Comparator<Task> comparator);

    List<Task> findAllByProjectId(String projectId);

    List<Task> removeAllByProjectId(String projectId);

    Task bindTaskByProject(String projectId, String taskId);

    Task unbindTaskFromProject(String taskId);

    Task findOneByName(String name);

    Task removeOneByName(String name);

}
