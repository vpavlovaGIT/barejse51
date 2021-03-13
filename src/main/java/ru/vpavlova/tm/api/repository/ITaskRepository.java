package ru.vpavlova.tm.api.repository;

import ru.vpavlova.tm.api.IBusinessRepository;
import ru.vpavlova.tm.entity.Task;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ITaskRepository extends IBusinessRepository<Task> {

    List<Task> findAll(Comparator<Task> comparator);

    List<Task> findAllByProjectId(String userId, String projectId);

    List<Task> removeAllByProjectId(String userId, String projectId);

    Optional<Task> bindTaskByProject(String userId, String projectId, String taskId);

    Optional<Task> unbindTaskFromProject(String userId, String taskId);

}
