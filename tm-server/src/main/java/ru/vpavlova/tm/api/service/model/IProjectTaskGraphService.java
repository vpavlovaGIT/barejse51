package ru.vpavlova.tm.api.service.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.TaskGraph;

import java.util.List;

public interface IProjectTaskGraphService {

    @NotNull
    List<TaskGraph> findAllTaskByProjectId(@Nullable String userId, @Nullable String projectId);

    void bindTaskByProject(@Nullable String userId, @Nullable String projectId, @Nullable String taskId);

    void unbindTaskFromProject(@Nullable String userId, @Nullable String taskId);

    void removeProjectById(@Nullable String userId, @Nullable String projectId);

}
