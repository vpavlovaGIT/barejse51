package ru.vpavlova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.api.IBusinessRepository;
import ru.vpavlova.tm.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository extends IBusinessRepository<Task> {

    @NotNull
    List<Task> findAllByProjectId(@NotNull String userId, @NotNull String projectId);

    @NotNull
    List<Task> removeAllByProjectId(@NotNull String userId, @NotNull String projectId);

    @NotNull
    Optional<Task> bindTaskByProject(@NotNull String userId, @NotNull String projectId, @NotNull String taskId);

    @NotNull
    Optional<Task> unbindTaskFromProject(@NotNull String userId, @NotNull String taskId);

}
