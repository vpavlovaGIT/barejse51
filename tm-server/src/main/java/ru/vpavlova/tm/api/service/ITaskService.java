package ru.vpavlova.tm.api.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IBusinessService;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.enumerated.Status;

import java.util.Optional;

public interface ITaskService extends IBusinessService<Task> {

    @NotNull
    Task add(
            @Nullable String userId,
            @Nullable String name,
            @Nullable String description
    );

    @SneakyThrows
    void remove(@Nullable Task entity);

    @SneakyThrows
    void clear(@Nullable String userId);

    @NotNull
    @SneakyThrows
    Optional<Task> findById(
            @Nullable String userId, @Nullable String id
    );

    @NotNull
    @SneakyThrows
    Optional<Task> findByIndex(
            @Nullable String userId, @Nullable Integer index
    );

    @NotNull
    @SneakyThrows
    Optional<Task> findByName(
            @Nullable String userId, @Nullable String name
    );

    @SneakyThrows
    void remove(
            @Nullable String userId, @Nullable Task entity
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
