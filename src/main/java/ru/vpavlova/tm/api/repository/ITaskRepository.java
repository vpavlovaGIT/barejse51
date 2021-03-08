package ru.vpavlova.tm.api.repository;

import ru.vpavlova.tm.api.IBusinessRepository;
import ru.vpavlova.tm.entity.Task;

import java.util.Comparator;
import java.util.List;

public interface ITaskRepository extends IBusinessRepository<Task> {

    List<Task> findAll(Comparator<Task> comparator);

    List<Task> findAllByProjectId(String userId, String projectId);

    List<Task> removeAllByProjectId(String userId, String projectId);

    Task bindTaskByProject(String userId, String projectId, String taskId);

    Task unbindTaskFromProject(String userId, String taskId);

    Task findOneByName(String userId, String name);

    Task removeOneByName(String userId, String name);

}
