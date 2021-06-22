package ru.vpavlova.tm.api.repository.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface ITaskDTORepository extends IRepositoryDTO<TaskDTO> {

    void clear();

    void clearByUserId(@NotNull String userId);

    @NotNull
    List<TaskDTO> findAll();

    @NotNull
    List<TaskDTO> findAllByProjectId(
            @NotNull String userId,
            @NotNull String projectId
    );

    @NotNull
    List<TaskDTO> findAllByUserId(@Nullable String userId);

    @NotNull
    Optional<TaskDTO> findById(@Nullable String id);

    @NotNull
    Optional<TaskDTO> findOneByIdAndUserId(
            @Nullable String userId, @NotNull String id
    );

    @NotNull
    Optional<TaskDTO> findOneByIndex(
            @Nullable String userId, @NotNull Integer index
    );

    void bindTaskByProjectId(
            @NotNull String userId,
            @NotNull String projectId,
            @NotNull String taskId
    );

    void unbindTaskFromProjectId(@NotNull String userId, @NotNull String id);

    @NotNull
    Optional<TaskDTO> findOneByName(
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
