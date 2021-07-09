package ru.vpavlova.tm.api.repository.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IGraphRepository;
import ru.vpavlova.tm.entity.TaskGraph;

import java.util.List;
import java.util.Optional;

public interface ITaskGraphRepository extends IGraphRepository<TaskGraph> {

    void clear();

    void clearByUserId(@NotNull String userId);

    @NotNull
    List<TaskGraph> findAll();

    @NotNull
    List<TaskGraph> findAllByProjectId(
            @NotNull String userId,
            @NotNull String projectId
    );

    @NotNull
    List<TaskGraph> findAllByUserId(@Nullable String userId);

    @NotNull
    Optional<TaskGraph> findById(@Nullable String id);

    @NotNull
    Optional<TaskGraph> findOneByIdAndUserId(
            @Nullable String userId, @NotNull String id
    );

    @NotNull
    Optional<TaskGraph> findOneByIndex(
            @Nullable String userId, @NotNull Integer index
    );

    void bindTaskByProjectId(
            @NotNull String userId,
            @NotNull String projectId,
            @NotNull String taskId
    );

    void unbindTaskFromProjectId(@NotNull String userId, @NotNull String id);

    @NotNull
    Optional<TaskGraph> findOneByName(
            @Nullable String userId, @NotNull String name
    );

    void removeAllByProjectId(
            @NotNull String userId,
            @NotNull String projectId
    );

    void removeById(@Nullable String id);

    void removeOneByIdAndUserId(@Nullable String userId, @NotNull String id);

    void removeOneByName(
            @Nullable String userId, @NotNull String name
    );

}
