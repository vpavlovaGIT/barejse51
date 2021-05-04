package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.service.IProjectTaskService;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;

import java.util.List;
import java.util.Optional;

public class ProjectTaskService implements IProjectTaskService {

    @NotNull
    private final IProjectRepository projectRepository;

    @NotNull
    private final ITaskRepository taskRepository;

    public ProjectTaskService(
            @NotNull final IProjectRepository projectRepository,
            @NotNull final ITaskRepository taskRepository
    ) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @NotNull
    @Override
    public List<Task> findAllTaskByProjectId(
            @Nullable final String userId,
            @Nullable final String projectId
    ) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (projectId == null || projectId.isEmpty()) throw new EmptyIdException();
         return taskRepository.findAllByProjectId(userId, projectId);
    }

    @Nullable
    @Override
    public Optional<Task> bindTaskByProject(
            @Nullable final String userId,
            @Nullable final String projectId,
            @Nullable final String taskId
    ) {
        if (projectId == null || projectId.isEmpty()) throw new EmptyIdException();
        if (taskId == null || taskId .isEmpty()) throw new EmptyIdException();
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        return taskRepository.bindTaskByProject(userId, projectId, taskId);
    }

    @Nullable
    @Override
    public Optional<Task> unbindTaskFromProject (
            @Nullable final String userId,
            @Nullable final String taskId
    ) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (taskId == null || taskId.isEmpty()) throw new EmptyIdException();
        return taskRepository.unbindTaskFromProject(userId, taskId);
    }

    @Nullable
    @Override
    public Project removeProjectById(
            @Nullable final String userId,
            @Nullable final String projectId
    ) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (projectId == null || projectId.isEmpty()) throw new EmptyIdException();
        taskRepository.removeAllByProjectId(userId, projectId);
        return projectRepository.removeById(userId, projectId);
    }

}
