package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.model.Project;
import ru.vpavlova.tm.model.Task;

import java.util.List;

public interface IProjectTaskService {

    List<Task> findAllTaskByProjectId(String projectId);

    Task bindTaskByProject(String projectId, String taskId);

    Task unbindTaskFromProject(String taskId);

    Project removeProjectById(String projectId);

}
