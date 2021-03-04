package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Task;

import java.util.List;

public interface IProjectTaskService {

    List<Task> findAllTaskByProjectId(String userId, String projectId);

    Task bindTaskByProject(String userId, String projectId, String taskId);

    Task unbindTaskFromProject(String userId, String taskId);

    Project removeProjectById(String userId, String projectId);

}
