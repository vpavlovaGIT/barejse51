package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.TaskDTO;

import java.util.List;

public interface IProjectTaskService {

    @NotNull
    List<TaskDTO> findAllTaskByProjectId(@Nullable String userId, @Nullable String projectId);

    void bindTaskByProject(@Nullable String userId, @Nullable String projectId, @Nullable String taskId);

    void unbindTaskFromProject(@Nullable String userId, @Nullable String taskId);

    void removeProjectById(@Nullable String userId, @Nullable String projectId);

}
