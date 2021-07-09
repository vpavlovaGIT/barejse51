package ru.vpavlova.tm.api.service.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.dto.Task;
import ru.vpavlova.tm.entity.TaskGraph;
import ru.vpavlova.tm.enumerated.Status;

import java.util.List;
import java.util.Optional;

public interface ITaskGraphService extends IGraphBusinessService<TaskGraph> {


    @NotNull
    TaskGraph add(
            @Nullable String userId,
            @Nullable String name,
            @Nullable String description
    );

    @SneakyThrows
    void remove(@Nullable TaskGraph entity);

    @SneakyThrows
    void clear(@Nullable String userId);

    @NotNull
    @SneakyThrows
    List<TaskGraph> findAll(@Nullable String userId);

    @NotNull
    @SneakyThrows
    Optional<TaskGraph> findOneById(
            @Nullable String userId, @Nullable String id
    );

    @NotNull
    @SneakyThrows
    Optional<TaskGraph> findOneByIndex(
            @Nullable String userId, @Nullable Integer index
    );

    @NotNull
    @SneakyThrows
    Optional<TaskGraph> findOneByName(
            @Nullable String userId, @Nullable String name
    );

    @SneakyThrows
    void remove(
            @Nullable String userId, @Nullable TaskGraph entity
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
