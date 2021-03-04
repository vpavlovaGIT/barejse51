package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Task;

import java.util.List;

public interface IProjectTaskService {

    List<Task> findAllTaskByProjectId(String projectId);

    Task bindTaskByProject(String projectId, String taskId);

    Task unbindTaskFromProject(String taskId);

    Project removeProjectById(String userId, String projectId);

}
