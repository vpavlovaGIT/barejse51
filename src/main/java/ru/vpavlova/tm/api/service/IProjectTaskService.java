package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.entity.User;

import java.util.List;
import java.util.Optional;

public interface IProjectTaskService {

    List<Task> findAllTaskByProjectId(String userId, String projectId);

    Optional<Task> bindTaskByProject(String userId, String projectId, String taskId);

    Optional<Task> unbindTaskFromProject(String userId, String taskId);

    Project removeProjectById(String userId, String projectId);

}
