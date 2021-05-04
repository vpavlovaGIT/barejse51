package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.entity.User;

import java.util.List;
import java.util.Optional;

public interface IProjectTaskService {

    @NotNull
    List<Task> findAllTaskByProjectId(@Nullable String userId, @Nullable String projectId);

    @Nullable
    Optional<Task> bindTaskByProject(@Nullable String userId, @Nullable String projectId, @Nullable String taskId);

    @Nullable
    Optional<Task> unbindTaskFromProject(@Nullable String userId, @Nullable String taskId);

    @Nullable
    Project removeProjectById(@Nullable String userId, @Nullable String projectId);

}
