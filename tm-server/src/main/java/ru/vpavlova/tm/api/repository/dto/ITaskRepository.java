package ru.vpavlova.tm.api.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IRepository;
import ru.vpavlova.tm.dto.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository extends IRepository<Task> {

    void clear();

    void clearByUserId(@NotNull String userId);

    @NotNull
    List<Task> findAll();

    @NotNull
    List<Task> findAllByProjectId(
            @NotNull String userId,
            @NotNull String projectId
    );

    @NotNull
    List<Task> findAllByUserId(@Nullable String userId);

    @NotNull
    Optional<Task> findById(@Nullable String id);

    @NotNull
    Optional<Task> findOneByIdAndUserId(
            @Nullable String userId, @NotNull String id
    );

    @NotNull
    Optional<Task> findOneByIndex(
            @Nullable String userId, @NotNull Integer index
    );

    void bindTaskByProjectId(
            @NotNull String userId,
            @NotNull String projectId,
            @NotNull String taskId
    );

    void unbindTaskFromProjectId(@NotNull String userId, @NotNull String id);

    @NotNull
    Optional<Task> findOneByName(
            @Nullable String userId, @NotNull String name
    );

    void removeAllByProjectId(
            @NotNull String userId,
            @NotNull String projectId
    );

    void removeOneById(@Nullable String id);

    void removeOneByIdAndUserId(@Nullable String userId, @NotNull String id);

    void removeOneByName(
            @Nullable String userId, @NotNull String name
    );

}
