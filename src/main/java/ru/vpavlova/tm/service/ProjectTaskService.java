package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.service.IProjectTaskService;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Task;

import java.util.List;

public class ProjectTaskService implements IProjectTaskService {

    private final IProjectRepository projectRepository;

    private final ITaskRepository taskRepository;

    public ProjectTaskService(final IProjectRepository projectRepository, final ITaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAllTaskByProjectId(final String userId, final String projectId) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (projectId == null || projectId.isEmpty()) throw new EmptyIdException();
         return taskRepository.findAllByProjectId(userId, projectId);
    }

    @Override
    public Task bindTaskByProject(final String userId, final String projectId, final String taskId) {
        if (projectId == null || projectId.isEmpty()) throw new EmptyIdException();
        if (taskId == null || taskId .isEmpty()) throw new EmptyIdException();
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        return taskRepository.bindTaskByProject(userId, projectId, taskId);
    }

    @Override
    public Task unbindTaskFromProject(final String userId, final String taskId) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (taskId == null || taskId.isEmpty()) throw new EmptyIdException();
        return taskRepository.unbindTaskFromProject(userId, taskId);
    }

    @Override
    public Project removeProjectById(final String userId, final String projectId) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (projectId == null || projectId.isEmpty()) throw new EmptyIdException();
        taskRepository.removeAllByProjectId(userId, projectId);
        return projectRepository.removeOneById(userId, projectId);
    }

}
