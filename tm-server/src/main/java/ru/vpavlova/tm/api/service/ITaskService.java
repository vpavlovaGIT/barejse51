package ru.vpavlova.tm.api.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IBusinessService;
import ru.vpavlova.tm.dto.TaskDTO;
import ru.vpavlova.tm.enumerated.Status;

import java.util.Optional;

public interface ITaskService extends IBusinessService<TaskDTO> {

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
    Optional<TaskDTO> findById(
            @Nullable String userId, @Nullable String id
    );

    @NotNull
    @SneakyThrows
    Optional<TaskDTO> findByIndex(
            @Nullable String userId, @Nullable Integer index
    );

    @NotNull
    @SneakyThrows
    Optional<TaskDTO> findByName(
            @Nullable String userId, @Nullable String name
    );

    @SneakyThrows
    void remove(
            @Nullable String userId, @Nullable TaskDTO entity
    );

    @SneakyThrows
    void removeById(
            @Nullable String userId, @Nullable String id
    );

    @SneakyThrows
    void removeByIndex(
            @Nullable String userId, @Nullable Integer index
    );

    @SneakyThrows
    void removeByName(
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
