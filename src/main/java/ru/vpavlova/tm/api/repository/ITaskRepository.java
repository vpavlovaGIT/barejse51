package ru.vpavlova.tm.api.repository;

import ru.vpavlova.tm.model.Task;
import java.util.List;

public interface ITaskRepository {

    List<Task> findAll();

    List<Task> findAllByProjectId(String projectId);

    List<Task> removeAllByProjectId(String projectId);

    Task bindTaskByProject(String projectId, String taskId);

    Task unbindTaskFromProject(String taskId);

    void add(Task task);

    void remove(Task task);

    void clear();

    Task findOneById(String id);

    Task findOneByIndex(Integer index);

    Task findOneByName(String name);

    Task removeOneById(String id);

    Task removeOneByIndex(Integer index);

    Task removeOneByName(String name);

}
