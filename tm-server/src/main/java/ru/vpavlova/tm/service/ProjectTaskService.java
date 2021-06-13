package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.IProjectTaskService;
import ru.vpavlova.tm.dto.TaskDTO;
import ru.vpavlova.tm.exception.empty.EmptyIdException;

import java.util.List;

public class ProjectTaskService implements IProjectTaskService {

    @NotNull
    private final IConnectionService connectionService;

    public ProjectTaskService(@NotNull IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @NotNull
    @Override
    public List<TaskDTO> findAllTaskByProjectId(
            @Nullable final String userId,
            @Nullable final String projectId
    ) {
        if (projectId == null || projectId.isEmpty()) throw new EmptyIdException();
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
        return taskRepository.findAllByProjectId(userId, projectId);
    }

    @Override
    @SneakyThrows
    public void bindTaskByProject(
            @Nullable final String userId,
            @Nullable final String projectId,
            @Nullable final String taskId
    ) {
        if (projectId == null || projectId.isEmpty()) throw new EmptyIdException();
        if (taskId == null || taskId.isEmpty()) throw new EmptyIdException();
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.bindTaskByProject(userId, projectId, taskId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void unbindTaskFromProject(
            @Nullable final String userId,
            @Nullable final String taskId
    ) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (taskId == null || taskId.isEmpty()) throw new EmptyIdException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            taskRepository.unbindTaskFromProject(userId, taskId);
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
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
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final ITaskRepository taskRepository = sqlSession.getMapper(ITaskRepository.class);
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            taskRepository.removeAllByProjectId(userId, projectId);
            projectRepository.removeOneByIdAndUserId(userId, projectId);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

}
