package ru.vpavlova.tm.api.service.dto;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.TaskDTO;
import ru.vpavlova.tm.enumerated.Status;

import java.util.List;
import java.util.Optional;

public interface ITaskDTOService extends IBusinessDTOService<TaskDTO> {

    @NotNull
    TaskDTO add(
            @Nullable String userId,
            @Nullable String name,
            @Nullable String description
    );

    @SneakyThrows
    void remove(@Nullable TaskDTO entity);

    @SneakyThrows
    void clear(@Nullable String userId);

    @NotNull
    @SneakyThrows
    List<TaskDTO> findAll(@Nullable String userId);

    @SneakyThrows
    @NotNull Optional<TaskDTO> findOneById(
            @Nullable String userId, @Nullable String id
    );

    @NotNull
    @SneakyThrows
    Optional<TaskDTO> findOneByIndex(
            @Nullable String userId, @Nullable Integer index
    );

    @NotNull
    @SneakyThrows
    Optional<TaskDTO> findOneByName(
            @Nullable String userId, @Nullable String name
    );

    @SneakyThrows
    void remove(
            @Nullable String userId, @Nullable TaskDTO entity
    );

    @SneakyThrows
    void removeOneById(
            @Nullable String userId, @Nullable String id
    );

    @SneakyThrows
    void removeOneByIndex(
            @Nullable String userId, @Nullable Integer index
    );

    @SneakyThrows
    void removeOneByName(
            @Nullable String userId, @Nullable String name
    );

    @SneakyThrows
    void changeStatusById(
            @Nullable String userId,
            @Nullable String id,
            @Nullable Status status
    );

    @SneakyThrows
    void updateById(
            @Nullable String userId,
            @Nullable String id,
            @Nullable String name,
            @Nullable String description
    );
}
