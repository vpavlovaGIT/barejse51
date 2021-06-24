package ru.vpavlova.tm.service.dto;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.dto.IProjectDTORepository;
import ru.vpavlova.tm.api.repository.dto.ITaskDTORepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.dto.IProjectTaskDTOService;
import ru.vpavlova.tm.dto.TaskDTO;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.empty.EmptyUserIdException;
import ru.vpavlova.tm.repository.dto.ProjectDTORepository;
import ru.vpavlova.tm.repository.dto.TaskDTORepository;

import javax.persistence.EntityManager;
import java.util.List;

public final class ProjectTaskDTOService implements IProjectTaskDTOService {

    @NotNull
    private final IConnectionService connectionService;

    public ProjectTaskDTOService(@NotNull IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @Override
    @SneakyThrows
    public void bindTaskByProject(
            @Nullable final String userId,
            @Nullable final String projectId,
            @Nullable final String taskId
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (projectId.isEmpty()) throw new EmptyIdException();
        if (taskId.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskDTORepository taskRepository = new TaskDTORepository(entityManager);
            taskRepository.bindTaskByProjectId(userId, projectId, taskId);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<TaskDTO> findAllByProjectId(
            @Nullable final String userId, @Nullable final String projectId
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (projectId.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final ITaskDTORepository taskRepository = new TaskDTORepository(entityManager);
        return taskRepository.findAllByProjectId(userId, projectId);
    }

    @Override
    @SneakyThrows
    public void removeProjectById(
            @Nullable final String userId, @Nullable final String projectId
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (projectId.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskDTORepository taskRepository = new TaskDTORepository(entityManager);
            @NotNull final IProjectDTORepository projectRepository = new ProjectDTORepository(entityManager);
            taskRepository.removeAllByProjectId(userId, projectId);
            projectRepository.removeOneByIdAndUserId(userId, projectId);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void unbindTaskFromProject(
            @Nullable final String userId, @Nullable final String taskId
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (taskId.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ITaskDTORepository taskRepository = new TaskDTORepository(entityManager);
            taskRepository.unbindTaskFromProjectId(userId, taskId);
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

}
