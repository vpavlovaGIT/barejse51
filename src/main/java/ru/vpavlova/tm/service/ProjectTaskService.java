package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.service.IProjectTaskService;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.model.Project;
import ru.vpavlova.tm.model.Task;

import java.util.List;

public class ProjectTaskService implements IProjectTaskService {

    private final IProjectRepository projectRepository;

    private final ITaskRepository taskRepository;

    public ProjectTaskService(final IProjectRepository projectRepository, final ITaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAllTaskByProjectId(final String projectId) {
        if (projectId == null || projectId.isEmpty()) throw new EmptyIdException();
         return taskRepository.findAllByProjectId(projectId);
    }

    @Override
    public Task bindTaskByProject(final String projectId, final String taskId) {
        if (projectId == null || projectId.isEmpty()) throw new EmptyIdException();
        if (taskId == null || taskId .isEmpty()) throw new EmptyIdException();
        return taskRepository.bindTaskByProject(projectId, taskId);
    }

    @Override
    public Task unbindTaskFromProject(final String taskId) {
        if (taskId == null || taskId.isEmpty()) throw new EmptyIdException();
        return taskRepository.unbindTaskFromProject(taskId);
    }

    @Override
    public Project removeProjectById(final String projectId) {
        if (projectId == null || projectId.isEmpty()) throw new EmptyIdException();
        taskRepository.removeAllByProjectId(projectId);
        return projectRepository.removeOneById(projectId);
    }

}
