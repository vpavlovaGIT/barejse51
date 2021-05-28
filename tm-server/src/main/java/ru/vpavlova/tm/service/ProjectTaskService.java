package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.IProjectTaskService;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.repository.ProjectRepository;
import ru.vpavlova.tm.repository.TaskRepository;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ProjectTaskService implements IProjectTaskService {

    @NotNull
    private final IConnectionService connectionService;

    public ProjectTaskService(@NotNull IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @NotNull
    @Override
    public List<Task> findAllTaskByProjectId(
            @Nullable final String userId,
            @Nullable final String projectId
    ) {
        if (projectId == null || projectId.isEmpty()) throw new EmptyIdException();
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        final Connection connection = connectionService.getConnection();
        final ITaskRepository taskRepository = new TaskRepository(connection);
        return taskRepository.findAllByProjectId(userId, projectId);
    }

    @Nullable
    @Override
    @SneakyThrows
    public Optional<Task> bindTaskByProject(
            @Nullable final String userId,
            @Nullable final String projectId,
            @Nullable final String taskId
    ) {
        if (projectId == null || projectId.isEmpty()) throw new EmptyIdException();
        if (taskId == null || taskId .isEmpty()) throw new EmptyIdException();
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        final Connection connection = connectionService.getConnection();
        try {
            final ITaskRepository taskRepository = new TaskRepository(connection);
            @Nullable Optional<Task> task = taskRepository.bindTaskByProject(userId, projectId, taskId);
            connection.commit();
            return task;
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public Optional<Task> unbindTaskFromProject (
            @Nullable final String userId,
            @Nullable final String taskId
    ) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (taskId == null || taskId.isEmpty()) throw new EmptyIdException();
        final Connection connection = connectionService.getConnection();
        try {
            final ITaskRepository taskRepository = new TaskRepository(connection);
            return taskRepository.unbindTaskFromProject(userId, taskId);
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    @SneakyThrows
    public void removeProjectById(
            @Nullable final String userId,
            @Nullable final String projectId
    ) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (projectId == null || projectId.isEmpty()) throw new EmptyIdException();
        final Connection connection = connectionService.getConnection();
        try {
            final ITaskRepository taskRepository = new TaskRepository(connection);
            final IProjectRepository projectRepository = new ProjectRepository(connection);
            taskRepository.removeAllByProjectId(userId, projectId);
            projectRepository.removeById(userId, projectId);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

}
