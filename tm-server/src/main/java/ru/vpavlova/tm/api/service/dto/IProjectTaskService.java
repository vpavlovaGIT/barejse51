package ru.vpavlova.tm.api.service.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Task;

import java.util.List;

public interface IProjectTaskService {

    void bindTaskByProject(
            @Nullable String userId, @Nullable String projectId, @Nullable String taskId
    );

    @NotNull
    List<Task> findAllByProjectId(@Nullable String userId, @Nullable String projectId);

    void removeProjectById(@Nullable String userId, @Nullable String projectId);

    void unbindTaskFromProject(@Nullable String userId, @Nullable String taskId);

}
